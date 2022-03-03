package geometry;

import geometry.*;

public class Vector extends TwoDMO {

  public Vector() { super(); }
  public Vector(float x, float y) { super(x, y); }
  public Vector(TwoDMO Other) { super(Other.x, Other.y); }
  // Vector between Points AB.
  public Vector(Point A, Point B) { super(B.x - A.x, B.y - A.y); }

  public String getDbg() { return "x: " + Float.toString(x) + "; y: " + Float.toString(y); }

  public float length2() { return x*x + y*y; }
  public float length() { return (float)Math.sqrt(length2()); }
  
  public float dot(Vector Other) { return x*Other.x + y*Other.y; }
  public void norm() { scale(1.f/length()); }
  public void scale(float Lenght) {
    x*=Lenght;
    y*=Lenght;
  }
  public Vector extend(float Length) { return new Vector(x*Length, y*Length); }
  public void add(Vector Other) { x+=Other.x; y+=Other.y; }

  public boolean isOrthogonally(Vector Other) {
    float dot = this.dot(Other) / this.length() / Other.length();
    float eps = 1e-1f;
    return dot <= eps && dot >= -eps ? true : false;
  }
}
