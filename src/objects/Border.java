package objects;

import java.awt.Graphics;
import geometry.*;

public class Border {
    // Left, up
    Point LU;
    // Right, down
    Point RD;

    public Border() { LU = new Point(0, 0); RD = new Point(100, 200); }
    public Border(Point LU, Point RD) { this.LU = LU; this.RD = RD; };

    public double getWidth() { return RD.getX() - LU.getX(); }
    public double getHeight() { return RD.getY() - LU.getY(); }

    public void draw(Graphics g) {
      g.drawRect((int)LU.getX(), (int)LU.getY(), (int)getWidth(), (int)getHeight());
    }
}
