package geometry;

import geometry.TwoDMO;

public class Vector extends TwoDMO {

  public Vector() { super(); }
  public Vector(float x, float y) { super(x, y); }
  public Vector(TwoDMO Other) { super(Other.x, Other.y); }

  public float length2() { return x*x + y*y; }
  public float lenght() { return (float)Math.sqrt(length2()); }
  
  public float dot(Vector Other) { return x*Other.x + y*Other.y; }
}
