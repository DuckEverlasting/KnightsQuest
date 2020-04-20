
public class XY {
  public int x, y;

  XY(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public XY add(XY addend) {
    return new XY(x + addend.x, y + addend.y);
  }

  public XY add(int addX, int addY) {
    return new XY(x + addX, y + addY);
  }

  public XY mult(XY multiplicand) {
    return new XY(x * multiplicand.x, y * multiplicand.y);
  }

  public XY mult(int multX, int multY) {
    return new XY(x * multX, y * multY);
  }
}