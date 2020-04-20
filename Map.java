import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Map {
  private Tiles tileSet;
  private int fillTileID = -1;
  private ArrayList<MappedTile> mappedTiles = new ArrayList<MappedTile>();
  private HashMap<Integer, String> comments = new HashMap<Integer, String>();
  private File mapFile;

  Map(File mapFile, Tiles tileSet) {
    this.tileSet = tileSet;
    this.mapFile = mapFile;
    try {
      Scanner scanner = new Scanner(mapFile);
      int lineIndex = 0;
      while(scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (!line.startsWith("//")) {
          String[] splitString;
          if (line.contains(":")) {
            splitString = line.split(":");
            if (splitString[0].equalsIgnoreCase("Fill")) {
              fillTileID = Integer.parseInt(splitString[1]);
            }
          } else {
            splitString = line.split(",");
            if (splitString.length >= 3) {
              MappedTile mappedTile = new MappedTile(
                Integer.parseInt(splitString[0]),
                new XY(
                  Integer.parseInt(splitString[1]),
                  Integer.parseInt(splitString[2])
                )
              );
              mappedTiles.add(mappedTile);
            }
          }
        } else {
          comments.put(lineIndex, line);
        }
        lineIndex++;
      }
      scanner.close();
    } catch(FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void setTile(int tileID, XY tileLoc) {
    boolean foundTile = false;
    for (int i = 0; i < mappedTiles.size(); i++) {
      MappedTile current = mappedTiles.get(i);
      if (current.loc.x == tileLoc.x && current.loc.y == tileLoc.y) {
        current.id = tileID;
        foundTile = true;
        break;
      }
    }
    if (!foundTile) {
      mappedTiles.add(new MappedTile(tileID, tileLoc));
    }
  }

  public void clearTile(XY tileLoc) {
    for (int i = 0; i < mappedTiles.size(); i++) {
      MappedTile current = mappedTiles.get(i);
      if (current.loc.x == tileLoc.x && current.loc.y == tileLoc.y) {
        mappedTiles.remove(current);
        break;
      }
    }
  }

  public void saveMap() {
    try {
      int lineIndex = 0;
      if (mapFile.exists()) {
        mapFile.delete();
      }

      PrintWriter printWriter = new PrintWriter(mapFile);

      while (comments.containsKey(lineIndex)) {
        printWriter.println(comments.get(lineIndex));
        lineIndex++;
      }

      if (fillTileID >= 0) {
        printWriter.println("Fill:" + fillTileID);
        lineIndex++;
      }

      for(int i = 0; i < mappedTiles.size(); i++) {
        while (comments.containsKey(lineIndex)) {
          printWriter.println(comments.get(lineIndex));
          lineIndex++;
        }
        MappedTile current = mappedTiles.get(i);
        printWriter.println(current.id + "," + current.loc.x + "," + current.loc.y);
        lineIndex++;
      }
      mapFile.createNewFile();
      printWriter.close();
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
  }

  public void render(RenderHandler renderer, XY zoom) {
    XY tileSize = Game.TILESIZE.mult(zoom);

    if (fillTileID >= 0) {
      Rectangle cam = renderer.getCamera();

      for (int y = cam.y - tileSize.y - (cam.y % tileSize.y); y < cam.h; y += tileSize.y) {
        for (int x = cam.x - tileSize.x - (cam.x % tileSize.x); x < cam.w; x += tileSize.x) {
          tileSet.renderTile(fillTileID, renderer, new XY(x, y), zoom);
        }
      }
    }

    for (int i = 0; i < mappedTiles.size(); i++) {
      MappedTile current = mappedTiles.get(i);
      tileSet.renderTile(current.id, renderer, current.loc.mult(tileSize), zoom);
    }
  }

  class MappedTile {
    public int id;
    public XY loc;

    public MappedTile(int id, XY loc) {
      this.id = id;
      this.loc = loc;
    }
  }
}