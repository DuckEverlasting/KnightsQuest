package KnightsQuest.classes;

public class Character {
  private int health;
  private int strength;
  private int dexterity;

  public Character(int health, int strength, int dexterity) {
    this.health = health;
    this.strength = strength;
    this.dexterity = dexterity;
  }

  public int getHealth() {
    return this.health;
  }

  public void setHealth(int num) {
    this.health = num;
  }

  public void incrementHealth(int num) {
    this.health += num;
  }

  public int getStrength() {
    return this.strength;
  }

  public void setStrength(int num) {
    this.strength = num;
  }

  public void incrementStrength(int num) {
    this.strength += num;
  }

  public int getDexterity() {
    return this.dexterity;
  }

  public void setDexterity(int num) {
    this.dexterity = num;
  }

  public void incrementDexterity(int num) {
    this.dexterity += num;
  }
}