package geometry;

import geometry.TwoDMO;

public class Point extends TwoDMO {

  public Point() { super(); }
  public Point(float x, float y) { super(x, y); }
  public Point(TwoDMO Other) { super(Other.x, Other.y); }

  public String getDbg() { return "x: " + Float.toString(x) + "; y: " + Float.toString(y); }

  public void move(TwoDMO Dir) { x+=Dir.getX(); y+=Dir.getY(); }
  public Point displace(TwoDMO Dir) { return new Point(x+Dir.getX(), y+Dir.getY()); }
}
