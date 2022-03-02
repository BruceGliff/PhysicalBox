package geometry;

import geometry.*;

public class Vector extends TwoDMO {

  public Vector() { super(); }
  public Vector(double x, double y) { super(x, y); }
  public Vector(TwoDMO Other) { super(Other.x, Other.y); }
  // Vector between Points AB.
  public Vector(Point A, Point B) { super(B.x - A.x, B.y - A.y); }

  public String getDbg() { return "x: " + Double.toString(x) + "; y: " + Double.toString(y); }

  public double length2() { return x*x + y*y; }
  public double length() { return Math.sqrt(length2()); }
  
  public double dot(Vector Other) { return x*Other.x + y*Other.y; }
  public void norm() { scale(1.0/length()); }
  public void scale(double Lenght) {
    x*=Lenght;
    y*=Lenght;
  }
  public Vector extend(double Length) { return new Vector(x*Length, y*Length); }
  public void add(Vector Other) { x+=Other.x; y+=Other.y; }
}
