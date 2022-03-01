package geometry;

// Two-dim math obj
public class TwoDMO {
  double x = 0;
  double y = 0;

  public TwoDMO() {}
  public TwoDMO(double x, double y) { setXY(x, y); }

  public double getX() { return x; };
  public double getY() { return y; };

  public void setX(double x) { this.x = x; };
  public void setY(double y) { this.y = y; };
  public void setXY(double x, double y) { this.x = x; this.y = y; };

  public void set(TwoDMO Other) { this.x = Other.x; this.y = Other.y; }

}
