package KnightsQuest.enums;

public enum BootsType {
  NONE("None", 0, 0),
  LEATHER("Leather Boots", 0, 10),
  PLATED("Plated Boots", 0, 20),
  SPIKED("Spiked Boots", 10, 10);

  public String name;
  public int power, armor;

  BootsType(String name, int power, int armor) {
    this.name = name;
    this.power = power;
    this.armor = armor;
  }
}