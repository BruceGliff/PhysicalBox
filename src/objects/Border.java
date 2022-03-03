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

    public float getWidth() { return RD.getX() - LU.getX(); }
    public float getHeight() { return RD.getY() - LU.getY(); }
    public Point getLU() { return LU; };
    public Point getRD() { return RD; };

    public void draw(Graphics g) {
      g.drawRect((int)LU.getX(), (int)LU.getY(), (int)getWidth(), (int)getHeight());
    }

    public HitResult[] checkHits(Point Points[]) {
      HitResult Hits[] = new HitResult[4];

      boolean AtLeastOneHit = false;
      for (int i = 0; i != 4; ++i) {
        Hits[i] = checkHit(Points[i], 0.f);
        AtLeastOneHit |= Hits[i].isHit();
      }

      if (!AtLeastOneHit)
        return Hits;

      for (int i = 0; i != 4; ++i)
        Hits[i] = checkHit(Points[i], 3);

      return Hits;
    }

    public HitResult checkHit(Point P, float Disp) {
      if (P.getX() <= LU.getX() + Disp)
        return new HitResult(new Vector(1, 0), new Point(LU.getX()+1, P.getY()));
      if (P.getX() >= RD.getX() - Disp)
        return new HitResult(new Vector(-1, 0), new Point(RD.getX()-1, P.getY()));
      if (P.getY() >= RD.getY() - Disp)
        return new HitResult(new Vector(0, -1), new Point(P.getX(), RD.getY()-1));
      if (P.getY() <= LU.getY() + Disp)
        return new HitResult(new Vector(0, 1), new Point(P.getX(), LU.getY()+1));

      return new HitResult();
    }
}
