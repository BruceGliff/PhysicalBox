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
        long NowHit = System.currentTimeMillis();
        long HitDelta = NowHit - PrevHit;
        PrevHit = NowHit;


        float Momentum = 0;
        float I = HalfDiag * HalfDiag * 0.333333f;
        float EnBefore = I * Spin * Spin + Velocity.length2();

        Vector Displacement = new Vector();

        // One of the hit points and velocity one the hit points.
        Point P = new Point();
        Vector V = new Vector();
        // Rad-vector if this point.
        Vector R = new Vector();
        for (int i = 0; i != 4; ++i) {
            if (Hits[i].isHit()) {
                P = Points[i];
                HitResult Hit = Hits[i];

                V = calcFullVelocity(P);
                Vector Norm = Hit.getNorm();
                R = new Vector(Position, P);
                Vector Vn0 = new Vector(Norm);
                Vn0.scale(-2.f * V.dot(Norm)); // deltaV along Norm
                V.add(Vn0);// Gets final point's Velocity vector;

                Displacement.add(new Vector(P, Hit.getHitPosition()));

                Momentum += R.getX() * Vn0.getY() - R.getY() * Vn0.getX();
            }
        }
        Spin += (1 / I) * Momentum;
        Velocity.set(V);
        Velocity.add(new Vector(R.getY(), -R.getX()).extend(Spin));

        float EnAfter = I * Spin * Spin + Velocity.length2();
        float Ratio = (float)Math.sqrt(EnBefore / EnAfter);

        float Coef = HitDelta < 200 ? 1.5f :  1.f;
        Spin *= Ratio / Coef;
        Velocity.scale(Ratio);

        Position.move(Displacement);
    
    }
}
