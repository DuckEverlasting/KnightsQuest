package KnightsQuest.enums;

public enum Weapons {
  UNARMED("Unarmed", 10, 0, 1, false),
  GREATSWORD("Great Sword", 30, 0, .8, true),
  RAPIER("Rapier", 20, 10, .9, false),
  KNIFE("Knife", 15, 0, .95, false),
  AXE("Axe", 30, 0, .7, true),
  CHAINSAW("Chainsaw", 50, 0, .6, true);

  public String name;
  public int power, armor;
  public double accuracy;
  public boolean twoHanded;

  Weapons(String name, int power, int armor, double accuracy, boolean twoHanded) {
    this.name = name;
    this.power = power;
    this.armor = armor;
    this.twoHanded = twoHanded;
  }
}