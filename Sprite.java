import java.awt.image.BufferedImage;

public class Sprite {
  protected XY dim;
  protected int[] pixels;

  Sprite(SpriteSheet sheet, XY loc, XY dim) {
    this.dim = dim;
    
    pixels = new int[dim.x * dim.y];
    sheet.getImage().getRGB(loc.x, loc.y, dim.x, dim.y, pixels, 0, dim.x);
  }

  Sprite(BufferedImage image) {
    dim = new XY(image.getWidth(), image.getHeight());

    pixels = new int[dim.x * dim.y];
    image.getRGB(0, 0, dim.x, dim.y, pixels, 0, dim.x);
  }

  Sprite() {};

  public XY getDim() {
    return dim;
  }

  public int[] getPixels() {
    return pixels;
  }
}