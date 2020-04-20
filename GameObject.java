
public interface GameObject extends UpdatableEntity {
  public void render(RenderHandler renderer, XY zoom);

  public void handleMouseClick(Rectangle mouseRectangle, Rectangle cam, XY zoom);
}