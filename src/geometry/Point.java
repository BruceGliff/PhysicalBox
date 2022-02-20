package geometry;

import geometry.TwoDMO;

public class Point extends TwoDMO {

  public Point() { super(); }
  public Point(float x, float y) { super(x, y); }
  public Point(TwoDMO Other) { super(Other.x, Other.y); }
}
