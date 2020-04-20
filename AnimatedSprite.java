public class AnimatedSprite extends Sprite implements UpdatableEntity {
  private Sprite[] cells;
  private String animDirection;
  private boolean isAlternating = false;
  private int frameRate;
  private int frameCount = 0;
  private int currentCell = 0;
  
  AnimatedSprite(SpriteSheet sheet, XY dim, int start, int end, int frameRate, String dir) {
    super();
    this.dim = dim;
    this.frameRate = frameRate;
    this.animDirection = dir;
    if (start > end) {
      System.out.println("Error: Illegal arguments in animated sprite. Start position placed after end position.");
    } else if (end - start < 2) {
      System.out.println("Error: Illegal arguments in animated sprite. Need at least two frames.");
    } else {
      buildCells(sheet, start, end);
    }
  }

  AnimatedSprite(SpriteSheet sheet, XY dim, int end, int frameRate, String dir) {
    this(sheet, dim, 0, end, frameRate, dir);
  }

  AnimatedSprite(SpriteSheet sheet, XY dim, int frameRate, String dir) {
    this(sheet, dim, 0, (sheet.SIZE.x / dim.x) * (sheet.SIZE.y / dim.y) - 1, frameRate, dir);
  }

  private void buildCells(SpriteSheet sheet, int start, int end) {
    cells = new Sprite[end - start + 1];

    for (int i = 0; i <= end - start; i++) {
      cells[i] = sheet.getSprite(i + start);
    }
  }

  @Override
  public XY getDim() {
    return cells[currentCell].getDim();
  }

  @Override
  public int[] getPixels() {
    return cells[currentCell].getPixels();
  }

  @Override
  public void update(Game game) {
    if (frameCount >= Game.FRAMERATE / frameRate) {
      frameCount = 0;
      incrementSprite();
    } else {
      frameCount++;
    }
  }

  public void incrementSprite() {
    switch (animDirection) {
      case "normal":
        if (currentCell < cells.length - 1) currentCell++;
        else currentCell = 0;
        break;
      case "reverse":
        if (currentCell > 0) currentCell--;
        else currentCell = cells.length - 1;
        break;
      case "alternate":
        if (isAlternating == false) {
          if (currentCell < cells.length - 2) {
            currentCell++;
          } else {
            currentCell++;
            isAlternating = true;
          }
        } else {
          if (currentCell > 1) {
            currentCell--;
          } else {
            currentCell--;
            isAlternating = false;
          }
        }
        break;   
      default:
        if (currentCell < cells.length - 1) currentCell++;
        else currentCell = 0;
        break;
    }
  }

  public void setCell(int num) {
    currentCell = num;
    isAlternating = false;
  }
}