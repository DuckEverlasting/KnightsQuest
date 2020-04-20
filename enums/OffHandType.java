package enums;

public enum OffHandType {
  NONE("None", 0, 0, 1, false),
  BUCKLER("Buckler", 0, 20, 1, true),
  GREATSHIELD("Greatshield", 0, 40, 1, true),
  SPIKED_SHIELD("Spiked Shield", 10, 20, 1, true),
  SIDE_BLADE("Side Blade", 20, 0, 1, true),
  CLOAK("Cloak", 10, 10, 1.1, false);

  public String name;
  public int power, armor;
  public double accuracy;
  public boolean shield;

  OffHandType(String name, int power, int armor, double accuracy, boolean shield) {
    this.name = name;
    this.power = power;
    this.armor = armor;
    this.accuracy = accuracy;
    this.shield = shield;
  }
}