package geometry;

import geometry.*;

public class Vector extends TwoDMO {

  public Vector() { super(); }
  public Vector(float x, float y) { super(x, y); }
  public Vector(TwoDMO Other) { super(Other.x, Other.y); }
  // Vector between Points AB.
  public Vector(Point A, Point B) { super(B.x - A.x, B.y - A.y); }

  public float length2() { return x*x + y*y; }
  public float lenght() { return (float)Math.sqrt(length2()); }
  
  public float dot(Vector Other) { return x*Other.x + y*Other.y; }
}
