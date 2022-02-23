package objects;

import java.awt.Graphics;
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
            int H = B.isPointInside(Points[i]);
            if (H != -1)
                calculateBounce(i, H);
        }
    }

    public void calculateBounce(int i, int H) {
        Point P = Points[i];
        // Vector via center
        Vector at = new Vector(P, Position);
        at.norm();
        // Vector perpend at;
        Vector ap = new Vector(at.getY(), -at.getX());
        ap.norm();

        // ABS velocity of all points 
        Vector[] AbsPVs = new Vector[4];
        for (int j = 0; j != 4; ++j) {
            Vector R = new Vector(Position, Points[j]);
            AbsPVs[j] = new Vector(-Spin * R.getY(), Spin * R.getX());
            AbsPVs[j].add(Velocity);
        }

        double vX = AbsPVs[i].getX();
        double vY = AbsPVs[i].getY();

        switch(H) {
            case 0:
              // up
              AbsPVs[i].setY(-vY);
              break;
            case 1:
              // right
              AbsPVs[i].setX(-vX);
              break;
            case 2:
              // down
              AbsPVs[i].setY(-vY);
              break;
            case 3:
              // left
              AbsPVs[i].setX(-vX);
              break;
          }
          

          // Get projections on axes via Center and perpendicular it.
          Vector AbsV_t[] = new Vector[4];
          Vector AbsV_p[] = new Vector[4];
          for (int j = 0; j != 4; ++j) {
            AbsV_t[j] = at.extend( AbsPVs[j].dot(at) );
            AbsV_p[j] = ap.extend( AbsPVs[j].dot(ap) );
          }

          double newVX_t = 0;
          double newVY_t = 0;
          double newVX_p = 0;
          double newVY_p = 0;
          for (int j = 0; j != 4; ++j) {
            newVX_t += AbsV_t[j].getX();
            newVY_t += AbsV_t[j].getY();
            // It has to be calculater different!!
            newVX_p += AbsV_p[j].getX();
            newVY_p += AbsV_p[j].getY();
          }

          Velocity.setX(newVX_t + newVX_p / 4);
          Velocity.setY(newVY_t + newVY_p / 4);

          // TODO calculate spin
    }
}
