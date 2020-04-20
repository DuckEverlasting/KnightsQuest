import java.awt.image.BufferedImage;

public class SpriteSheet {
  private int[] pixels;
  private BufferedImage image;
  public final XY SIZE;
  private XY spriteSize;

  private Sprite[] loadedSprites = null;
  private boolean spritesLoaded = false;

  SpriteSheet(BufferedImage sheetImage) {
    image = sheetImage;
    SIZE = new XY(sheetImage.getWidth(), sheetImage.getHeight());

    pixels = new int[SIZE.x * SIZE.y];
    pixels = sheetImage.getRGB(0, 0, SIZE.x, SIZE.y, pixels, 0, SIZE.x);
  }

  public void loadSprites(XY spriteDim) {
    this.spriteSize = new XY(spriteDim.x, spriteDim.y);

    loadedSprites = new Sprite[(SIZE.x / spriteDim.x) * (SIZE.y / spriteDim.y)];

    int spriteID = 0;
    for (int y = 0; y < SIZE.y; y += spriteDim.y) {
      for (int x = 0; x < SIZE.x; x += spriteDim.x) {
        loadedSprites[spriteID] = new Sprite(this, new XY(x, y), spriteDim);
        spriteID++;
      }
    }
    this.spritesLoaded = true;
  }

  public Sprite getSprite(XY loc) {
    if(spritesLoaded) {
      int spriteID = loc.y * (SIZE.x / spriteSize.x) + loc.x;

      if (spriteID < loadedSprites.length) {
        return loadedSprites[spriteID];
      } else {
        System.out.println("ERROR: Sprite ID is invalid.");
      }
    } else {
      System.out.println("ERROR: No sprites loaded.");
    }
    return null;
  }

  public Sprite getSprite(int spriteID) {
    if(spritesLoaded) {
      if (spriteID < loadedSprites.length) {
        return loadedSprites[spriteID];
      } else {
        System.out.println("ERROR: Sprite ID is invalid.");
      }
    } else {
      System.out.println("ERROR: No sprites loaded.");
    }
    return null;
  }

  public int[] getPixels() {
    return pixels;
  }

  public BufferedImage getImage() {
    return image;
  }

  public XY getSpriteSize() {
    return spriteSize;
  }
}
