
public abstract class GUIButton implements GameObject {

  protected Sprite sprite;
  protected Rectangle rect;
  protected boolean fixed;

  GUIButton(Sprite sprite, Rectangle rect, boolean fixed) {
    this.sprite = sprite;
    this.rect = rect;
    this.fixed = false;
  }

  GUIButton(Sprite sprite, Rectangle rect) {
    this(sprite, rect, false);
  }

  @Override
  public void render(RenderHandler renderer, XY zoom) {
    renderer.renderSprite(sprite, rect.getLoc(), zoom, fixed);
  }

  
  @Override
  public void handleMouseClick(Rectangle mouseRect, Rectangle cam, XY zoom) {
    if (mouseRect.intersects(rect)) {
      activate();
    }
  }
  
  public void update(Game game) {}

  public abstract void activate();

}