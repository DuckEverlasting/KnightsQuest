public class Rectangle {
  public int x, y, w, h;
  private int[] pixels;

  Rectangle(int x, int y, int w, int h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  Rectangle(XY loc, XY dim) {
    this.x = loc.x;
    this.y = loc.y;
    this.w = dim.x;
    this.h = dim.y;
  }

  Rectangle() {
    this.x = 0;
    this.y = 0;
    this.w = 0;
    this.h = 0;
  }

  public void generateGraphics(int color) {
    pixels = new int[w*h];
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = color;
    }
  }

  public void generateGraphics(int borderWidth, int color) {
    pixels = new int[w*h];
    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        if (y < borderWidth || h - y <= borderWidth || x < borderWidth || w - x <= borderWidth) {
          pixels[y * w + x] = color;
        } else {
          pixels[y * w + x] = Game.alpha;
        }
      }
    }
  }

  public int[] getPixels() {
    if (pixels != null) {
      return pixels;
    } else {
      System.out.println("ERROR: Can't get pixels when there are no pixels to be got.");
      return null;
    }
  }

  public boolean intersects(Rectangle rect) {
    if (x > rect.x + rect.w || rect.x > x + w) {
      return false;
    } else if (y > rect.y + rect.h || rect.y > y + h) {
      return false;
    }
    return true;
  }

  public XY getLoc() {
    return new XY(x, y);
  }

  public XY getDim() {
    return new XY(w, h);
  }
}