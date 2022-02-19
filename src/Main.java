import java.awt.*;
import java.applet.*;

import decoy.Decoy;

public class Main extends Applet
{
  String DecoyMsg;

  public void init() {
    Decoy Dcy = new Decoy();
    DecoyMsg = Dcy.decoy_msg();
    setForeground(Color.white);
    setBackground(Color.blue);
  }
  public void paint(Graphics g) {
    g.drawString("Welcome To Java Applet " + DecoyMsg ,40,50);
  }
}
