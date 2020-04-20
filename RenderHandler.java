import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class RenderHandler {

  private BufferedImage view;
  private int[] pixels;
  private Rectangle cam;

	public RenderHandler(int width, int height) {
    view = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    cam = new Rectangle(0, 0, width, height);

    pixels = ((DataBufferInt) view.getRaster().getDataBuffer()).getData();
  }
  
  public Rectangle getCamera() {
    return cam;
  }

	public void render(Graphics graphics) {
    graphics.drawImage(view, 0, 0, view.getWidth(), view.getHeight(), null);
  }
  
  public void renderImage(BufferedImage image, XY loc, XY zoom) {
    int[] imagePixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    renderArray(imagePixels, loc, new XY(image.getWidth(), image.getHeight()), zoom);
  }
  
  public void renderRectangle(Rectangle rectangle, XY zoom) {
    int[] rectanglePixels = rectangle.getPixels();
    if (rectanglePixels != null) {
      renderArray(rectanglePixels, new XY(rectangle.x, rectangle.y), new XY(rectangle.w, rectangle.h), zoom);
    }
  }

  public void renderSprite(Sprite sprite, XY loc, XY zoom) {
    renderArray(sprite.getPixels(), loc, sprite.getDim(), zoom);
  }

  public void renderArray(int[] renderPixels, XY loc, XY dim, XY zoom) {
    for (int y = 0; y < dim.y * zoom.y; y++) {
      for (int x = 0; x < dim.x * zoom.x; x++) {
        setPixel(renderPixels[(y / zoom.y) * dim.x + (x / zoom.x)], new XY(x + loc.x, y + loc.y));
      }  
    }
  }

  private void setPixel(int pixel, XY loc) {
    if (loc.x >= cam.x && loc.y >= cam.y && loc.x < cam.x + cam.w && loc.y <= cam.y + cam.h) {
      int pixelIndex = (loc.y - cam.y) * view.getWidth() + (loc.x - cam.x);
      if (pixels.length > pixelIndex && 0 <= pixelIndex && pixel != Game.alpha) {
        pixels[pixelIndex] = pixel;
      }
    }
  }

  public void clear() {
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = 0;
    }
  }
}