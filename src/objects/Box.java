package objects;

import java.awt.Graphics;
import java.net.SocketPermission;

import geometry.*;
import objects.*;

public class Box {
    Point[] Points;

    float HalfDiag;
    Point Position;
    float Angle;

    Vector Velocity;
    float Spin;

    long PrevHit;

    // Specify exactly 4 points.
    public Box(Point Position, float Angle, float Width, Vector Velocity, float Spin) {
        this.Position = Position;
        this.Angle = Angle;
        this.HalfDiag = Width * 0.707f; //  Width/sqrt(2)
        this.Velocity = Velocity;
        this.Spin = Spin;
        this.Points = new Point[4];
        this.PrevHit = 0;
        calculatePoints();
    }

    void calculatePoints() {
        float InitAngle = (float)Math.PI/2;
        for (int i = 0; i != 4; ++i) {
            float LocalAngle = InitAngle * i + (float)Math.PI/4 + Angle;
            Vector Direction = new Vector((float)Math.cos(LocalAngle), (float)Math.sin(LocalAngle));
            Direction.scale(HalfDiag);
            Points[i] = Position.displace(Direction);
        }
    }

    public void draw(Graphics G) {
        for (int i = 0; i != 4; ++i) {
            Point P1 = Points[i];
            Point P2 = Points[(i+1)%4];
            G.drawLine((int)P1.getX(), (int)P1.getY(), (int)P2.getX(), (int)P2.getY());
        }
    }

    public void process(Vector Force, float Delta) {
        float Time = Delta / 1000;
        
        Position.move(Velocity.extend(Time));
        Velocity.add(Force.extend(Time));
        Angle += Spin * Time;

        calculatePoints();
    }

    public void checkIntersection(Border B) {
        HitResult Hits[] = B.checkHits(Points);
        for (int i = 0; i != 4; ++i)
            if (Hits[i].isHit()) {
                calculateBounce(Hits);
                break;
            }
    }

    public Vector calcFullVelocity(Point P) {
        Vector R = new Vector(Position, P);
        Vector V = new Vector(Velocity);
        Vector AngleVel = new Vector(-R.getY(), R.getX());
        AngleVel.scale(Spin);
        V.add(AngleVel);
        return V;
    }

    public void calculateBounce(HitResult Hits[]) {
        float I = HalfDiag * HalfDiag * 0.333333f;
        float EnBefore = I * Spin * Spin + Velocity.length2();

        Vector AverageNorm = new Vector();
        float HitCenterX = 0;
        float HitCenterY = 0;
        int HitPoints = 0;

        for (int i = 0; i != 4; ++i) {
            if (Hits[i].isHit()) {
                HitPoints++;
                Point P = Points[i];
                HitResult Hit = Hits[i];

                AverageNorm.add(Hit.getNorm());
                HitCenterX += P.getX();
                HitCenterY += P.getY();
            }
        }
        AverageNorm.norm();
        Point HitCenter = new Point(HitCenterX / HitPoints, HitCenterY / HitPoints);

        Vector R = new Vector(Position, HitCenter);
        Vector Vn0 = new Vector(AverageNorm);
        Vn0.scale(-2.0f * Vn0.dot(Velocity)); // The deltaVn and also the Force.

        float Momentum = R.getX() * Vn0.getY() - R.getY() * Vn0.getX();
        Spin += (1 / I) * Momentum;

        Velocity.add(Vn0);

        float EnAfter = I * Spin * Spin + Velocity.length2();
        float Ratio = (float)Math.sqrt(EnBefore / EnAfter);
        Spin *= Ratio * 0.6;
        Velocity.scale( Ratio );

        if (Velocity.length2() < 500)
            Velocity.setXY(0, 0);

        AverageNorm.scale(1.f);
        Position.move(AverageNorm);    
    }
}
