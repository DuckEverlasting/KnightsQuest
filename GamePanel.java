import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
  private boolean play = false;
  // GAME STATE GOES HERE

  public GamePanel() {
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
  }

  public void paint(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(1, 1, 692, 592);

    g.setColor(Color.BLUE);
    g.fillRect(0, 0, 3, 592);
    g.fillRect(0, 0, 692, 8);
    g.fillRect(681, 0, 8, 592);
    g.fillRect(0, 559, 692, 8);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub

  }
 
}