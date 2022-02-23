package geometry;

import geometry.*;

public class Point extends TwoDMO {

  public Point() { super(); }
  public Point(double x, double y) { super(x, y); }
  public Point(TwoDMO Other) { super(Other.x, Other.y); }

  public String getDbg() { return "x: " + Double.toString(x) + "; y: " + Double.toString(y); }

  public void move(Vector Dir) { x+=Dir.getX(); y+=Dir.getY(); }
  public Point displace(Vector Dir) { return new Point(x+Dir.getX(), y+Dir.getY()); }
}
