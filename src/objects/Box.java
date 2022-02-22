package objects;

import java.awt.Graphics;
import geometry.*;

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
            Vector Direction = new Vector (Math.cos(LocalAngle), Math.sin(LocalAngle));
            Direction.scale(HalfDiag);
            Points[i] = Position.displace(Direction);
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i != 4; ++i) {
            g.drawString(Points[i].getDbg(), 200, 100+100*i);
            Point P1 = Points[i];
            Point P2 = Points[(i+1)%4];
            g.drawLine((int)P1.getX(), (int)P1.getY(), (int)P2.getX(), (int)P2.getY());
        }
    }
}
