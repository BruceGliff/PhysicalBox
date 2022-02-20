import java.awt.*;
import java.applet.*;

import geometry.*;

public class Main extends Applet
{
  Vector p1;
  int i = 1;

  public void init() {
    Vector p = new Vector(2,3);
    p1 = new Vector(p);
    setForeground(Color.white);
    setBackground(Color.white);
  }
  public void paint(Graphics g) {
    g.drawString("Welcome To Java Applet " + Integer.toString(i++) + Float.toString(p1.getY()), 40, 50);
    repaint();
  }
}
