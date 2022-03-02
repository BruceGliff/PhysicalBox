package geometry;

import geometry.*;

public class HitResult {

  boolean IsHit;
  Vector Normal;
  Point HitPosition;

  public HitResult() {
    IsHit = false;
  }

  public HitResult(Vector Normal, Point HitPosition) {
      this.Normal = new Vector(Normal);
      this.Normal.norm();
      this.HitPosition = new Point(HitPosition);
      this.IsHit = true;
  }

  public boolean isHit() { return IsHit; }
  public Vector getNorm() { return Normal; }
  public Point getHitPosition() { return HitPosition; }
};
