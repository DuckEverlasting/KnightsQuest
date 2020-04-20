import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseEventListener implements MouseListener, MouseMotionListener {

  private Game game;

  MouseEventListener(Game game) {
    this.game = game;
  }
  
  @Override
  public void mousePressed(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      game.leftClick(new XY(e.getX(), e.getY()));
    }

    if (e.getButton() == MouseEvent.BUTTON3) {
      game.rightClick(new XY(e.getX(), e.getY()));
    }
  }
  
  public void mouseReleased(MouseEvent e) {}

  public void mouseClicked(MouseEvent e) {}
  
  public void mouseMoved(MouseEvent e) {}
  
  public void mouseDragged(MouseEvent e) {}
  
  public void mouseEntered(MouseEvent e) {}
  
  public void mouseExited(MouseEvent e) {}

}