package geometry;

// Two-dim math obj
public class TwoDMO {
  float x = 0;
  float y = 0;

  public TwoDMO() {}
  public TwoDMO(float x, float y) { setXY(x, y); }

  public float getX() { return x; };
  public float getY() { return y; };

  public void setX(float x) { this.x = x; };
  public void setY(float y) { this.y = y; };
  public void setXY(float x, float y) { this.x = x; this.y = y; };

}
