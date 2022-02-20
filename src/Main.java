import java.awt.*;
import java.applet.*;

import geometry.*;
import objects.*;

public class Main extends Applet
{
  Border PlayBorder;

  public void init() {
    setForeground(Color.white);
    setBackground(Color.white);

    PlayBorder = new Border((10,10), (210, 110));

  }
  public void paint(Graphics g) {
    g.drawString("Welcome To Java Applet " + Integer.toString(i++) + Float.toString(p1.getY()), 40, 50);
    repaint();
  }
}
