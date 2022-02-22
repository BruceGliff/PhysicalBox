import java.awt.*;
import java.applet.*;

import geometry.*;
import objects.*;

public class Main extends Applet
{
  Border PlayBorder;

  public void init() {
    // setForeground(Color.white);
    // setBackground(Color.white);

    PlayBorder = new Border(new geometry.Point(10,10), new geometry.Point(900, 600));
    
    resize(910, 610);
  }
  public void paint(Graphics g) {
    g.drawString("Welcome To Java Applet ", 40, 50);
    PlayBorder.draw(g);
    repaint();
  }
}
