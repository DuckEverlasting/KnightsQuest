
public class GUI implements GameObject {

  private Sprite bgSprite;
  private GUIButton[] buttons;
  private Rectangle rect;
  private boolean fixed;

  GUI(Sprite bgSprite, GUIButton[] buttons, XY loc, boolean fixed) {
    this.bgSprite = bgSprite;
    this.buttons = buttons;
    this.fixed = fixed;
    XY dim = bgSprite == null ? new XY(0, 0) : bgSprite.getDim();
    rect = new Rectangle(loc, dim);
  }

  GUI(GUIButton[] buttons, XY loc, boolean fixed) {
    this(null, buttons, loc, fixed);
  }

  @Override
  public void render(RenderHandler renderer, XY zoom) {
    if (bgSprite != null) {
      renderer.renderSprite(bgSprite, this.rect.getLoc(), zoom, fixed);
    }

    if (buttons != null) {
      for (int i = 0; i < buttons.length; i++) {
        buttons[i].render(renderer, zoom);
      }
    }
  }

  @Override
  public void update(Game game) {

  }

  @Override
  public void handleMouseClick(Rectangle mouseRect, Rectangle cam, XY zoom) {
    if (!fixed) {
      mouseRect = new Rectangle(mouseRect.getDim().add(cam.getDim()), mouseRect.getLoc());
    }
    
    if (rect.w == 0 || rect.h == 0 || mouseRect.intersects(rect)) {
      for (int i = 0; i < buttons.length; i++) {
        buttons[i].handleMouseClick(mouseRect, cam, zoom);
      }
    }
  }

}