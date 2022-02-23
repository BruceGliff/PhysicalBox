import java.awt.*;
import java.applet.*;

import geometry.*;
import objects.*;

public class Main extends Applet
{
  Border PlayBorder;
  Box PlayBox;

  long StartFrameTime;
  long Delta;
  Vector Force;

  public void init() {
    // setForeground(Color.white);
    // setBackground(Color.white);

    PlayBorder = new Border(new geometry.Point(10,10), new geometry.Point(900, 600));
    PlayBox = new Box(new geometry.Point(200, 200), Math.PI/4, 150.0, new Vector(), 0.1);
    Force = new Vector(0, 70);

    Delta = 0;

    resize(910, 610);
  }
  public void paint(Graphics g) {
    StartFrameTime = System.currentTimeMillis();
    g.drawString("Welcome To Java Applet ", 40, 50);
    PlayBorder.draw(g);
    PlayBox.draw(g);

    try {
        Thread.sleep(10);
    } catch (Exception e) {
        e.printStackTrace();
    }

    PlayBox.checkIntersection(PlayBorder);
    PlayBox.process(Force, Delta);
    repaint();

    Delta = System.currentTimeMillis() - StartFrameTime;
    
  }
}
