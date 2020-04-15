package KnightsQuest.enums;

public enum HelmType {
  NONE("None", 0, 0),
  LEATHER("Leather Cap", 0, 10),
  PLATED("Plated Helm", 0, 20),
  SPIKED("Spiked Hat", 10, 10);

  public String name;
  public int power, armor;

  HelmType(String name, int power, int armor) {
    this.name = name;
    this.power = power;
    this.armor = armor;
  }
}