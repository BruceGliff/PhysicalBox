package objects;

import java.awt.*;
import geometry.*;

public class Border {
    // Left, up
    Point LU;
    // Right, down
    Point RD;

    public Border() { LU = new Point(0, 0); RD = new Point(100, 200); }
    public Border(Point LU, Point RD) { this.LU = LU; this.RU = RD; };

    public float getWidth() { return RD.getX() - LU.getX(); }
    public float getHeight() { return LU.getY() - RD.getY(); }
}
