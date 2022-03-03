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
    PlayBorder = new Border(new geometry.Point(10,10), new geometry.Point(900, 600));
    PlayBox = new Box(new geometry.Point(200, 200),  (float)Math.PI/4, 150.0f, new Vector(-100, 100), 0.1f);
    Force = new Vector(0, 200);

    Delta = 0;

    resize(910, 610);
  }
  public void paint(Graphics g) {
    StartFrameTime = System.currentTimeMillis();
    PlayBorder.draw(g);
    PlayBox.draw(g);

    try {
        Thread.sleep(40);
    } catch (Exception e) {
        e.printStackTrace();
    }

    PlayBox.process(Force, Delta);
    PlayBox.checkIntersection(PlayBorder);
    
    repaint();

    Delta = System.currentTimeMillis() - StartFrameTime;
    
  }
}
