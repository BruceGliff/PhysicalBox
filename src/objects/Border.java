package objects;

import java.awt.Graphics;

import javax.swing.text.Position;

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
        Hits[i] = checkHit(Points[i], 5);

      return Hits;
    }

    public HitResult checkHit(Point P, float Disp) {

      Vector Norm = new Vector();
      boolean IsHit = false;
      if (P.getX() <= LU.getX() + Disp) {// West/Left. 
        Norm.add(new Vector(1, 0));
        IsHit = true;
      }
      if (P.getX() >= RD.getX() - Disp) {// East/Right.
        Norm.add(new Vector(-1, 0));
        IsHit = true;
      }
      if (P.getY() >= RD.getY() - Disp) {// South/Down.
        Norm.add(new Vector(0, -1));
        IsHit = true;
      }
      if (P.getY() <= LU.getY() + Disp) {// North/Up.
        Norm.add(new Vector(0, 1));
        IsHit = true;
      }
      if (!IsHit)
        return new HitResult();

      Norm.norm();
      return new HitResult(Norm, new Point());
    }
}
