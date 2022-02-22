package objects;

import java.awt.Graphics;
import geometry.*;

public class Box {
    Point[] Points;

    float HalfDiag;
    Point Position;
    float Angle;

    Vector Velocity;
    float Spin;

    // Specify exactly 4 points.
    public Box(Point Position, float Angle, float Width, Vector Velocity, float Spin) {
        this.Position = Position;
        this.Angle = Angle;
        this.HalfDiag = Width * (float)0.707; //  Width/sqrt(2)
        this.Velocity = Velocity;
        this.Spin = Spin;
        // this.Points = new Points[4];

        calculatePoints();
    }

    void calculatePoints() {
        // float InitAngle = 0;
        // // TODO iterate with pi/4: 0, pi/4, pi/2, 3pi/4.
        // // TODO think about angle
        // for (int i = 0; i != 4; ++i, InitAngle = InitAngle * i + pi/4) {
        //     Point P = Points[i];

        //     Vector Direction = new Vector (Math.cos(InitAngle), Math.sin(InitAngle));
        //     Direction *= HalfDiag; // TODO implement
        //     P = Position + Direction;
        // }
    }

    public void draw(Graphics g) {  }
}
