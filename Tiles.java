import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tiles {
  private SpriteSheet spriteSheet;
  private ArrayList<Tile> tilesList = new ArrayList<Tile>();

  Tiles(File tilesFile, SpriteSheet spriteSheet) {
    this.spriteSheet = spriteSheet;
    try {
      Scanner scanner = new Scanner(tilesFile);
      while(scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (!line.startsWith("//")) {
          String[] splitString = line.split(",");
          String tileName = splitString[0];
          XY spriteLoc = new XY(
            Integer.parseInt(splitString[1]),
            Integer.parseInt(splitString[2])
          );
          Tile tile = new Tile(tileName, spriteSheet.getSprite(spriteLoc));
          tilesList.add(tile);
        }
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public SpriteSheet getSpriteSheet() {
    return spriteSheet;
  }

  public void renderTile(int tileID, RenderHandler renderer, XY loc, XY zoom) {
    if (tileID >= 0 && tilesList.size() > tileID) {
      renderer.renderSprite(tilesList.get(tileID).sprite, loc, zoom);
    } else {
      System.out.println("ERROR: selected tile out of range.");
    }
  }

  private class Tile {
    public String tileName;
    public Sprite sprite;

    Tile(String tileName, Sprite sprite) {
      this.tileName = tileName;
      this.sprite = sprite;
    }
  }
}