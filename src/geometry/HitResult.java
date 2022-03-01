package geometry;

import geometry.*;

public class HitResult {

  boolean IsHit;
  Vector Normal;

  public HitResult() {
    IsHit = false;
  }

  public HitResult(Vector Normal) {
      this.Normal = new Vector(Normal);
      this.Normal.norm();
      this.IsHit = true;
  }

  public boolean isHit() { return IsHit; }
  public Vector getNorm() { return Normal; }
};
