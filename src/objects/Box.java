package objects;

import java.awt.Graphics;
import java.net.SocketPermission;

import geometry.*;
import objects.*;

public class Box {
    Point[] Points;

    double HalfDiag;
    Point Position;
    double Angle;

    Vector Velocity;
    double Spin;

    // Specify exactly 4 points.
    public Box(Point Position, double Angle, double Width, Vector Velocity, double Spin) {
        this.Position = Position;
        this.Angle = Angle;
        this.HalfDiag = Width * 0.707; //  Width/sqrt(2)
        this.Velocity = Velocity;
        this.Spin = Spin;
        this.Points = new Point[4];
        calculatePoints();
    }

    void calculatePoints() {
        double InitAngle = Math.PI/2;
        for (int i = 0; i != 4; ++i) {
            double LocalAngle = InitAngle * i + Math.PI/4 + Angle;
            Vector Direction = new Vector(Math.cos(LocalAngle), Math.sin(LocalAngle));
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

    public void process(Vector Force, double Delta) {
        double Time = Delta / 1000;
        
        Position.move(Velocity.extend(Time));
        Velocity.add(Force.extend(Time));
        Angle += Spin * Time;

        calculatePoints();
    }

    public void checkIntersection(Border B) {
        for (int i = 0; i != 4; ++i) {
            HitResult Hit = B.checkHit(Points[i]);
            // int H = B.isPointInside(Points[i]);
            if (Hit.isHit())
                calculateBounce(i, Hit);
        }
    }

    public void calculateBounce(int i, HitResult Hit) {
        Point P = Points[i];
        double I = HalfDiag * HalfDiag * 0.333333;
        Vector R = new Vector(Position, P);

        double EnBefore = I * Spin * Spin + Velocity.length2();

        Vector V = new Vector(Velocity);
        Vector AngleVel = new Vector(-R.getY(), R.getX());
        AngleVel.scale(Spin);
        V.add(AngleVel);
        
        Vector Norm = Hit.getNorm();
        Vector Vn0 = new Vector(Norm);
        Vn0.scale(-2.0 * V.dot(Norm)); // deltaV along Norm
        
        V.add(Vn0);// Gets final point's Velocity vector;
        
        Spin += (1 / I) * (R.getX() * Vn0.getY() - R.getY() * Vn0.getX());
        
        Velocity.set(V);
        Velocity.add(new Vector(R.getY(), -R.getX()).extend(Spin));

        double EnAfter = I * Spin * Spin + Velocity.length2();
        double Ratio = Math.sqrt(EnBefore / EnAfter);

        System.out.println(Ratio);
        Spin *= Ratio;
        Velocity.scale(Ratio);

        Vector Displace = new Vector(P, Hit.getHitPosition());
        Position.move(Displace);
    }
}
