package enums;

public enum ArmorType {
  NONE("None", 0, 0),
  LEATHER("Leather Armor", 0, 20),
  PLATED("Plated Armor", 0, 40),
  SPIKED("Spiked Armor", 10, 20);

  public String name;
  public int power, armor;

  ArmorType(String name, int power, int armor) {
    this.name = name;
    this.power = power;
    this.armor = armor;
  }
}