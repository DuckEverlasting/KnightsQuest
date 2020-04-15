package KnightsQuest.classes;

import KnightsQuest.util.Validation;

public class Character {
  private int health;
  private int maxHealth;
  private int strength;
  private int dexterity;

  public Character(int health, int strength, int dexterity) {
    this.health = health;
    this.maxHealth = health;
    this.strength = strength;
    this.dexterity = dexterity;
  }

  public int getHealth() {
    return this.health;
  }

  public void setHealth(int num) {
    num = Validation.validateInteger(num, 0, this.maxHealth);
    this.health = num;
  }

  public void incrementHealth(int num) {
    num = Validation.validateInteger(num, 0, this.maxHealth);
    this.health += num;
  }

  public int getStrength() {
    return this.strength;
  }

  public void setStrength(int num) {
    num = Validation.validateInteger(num);
    this.strength = num;
  }

  public void incrementStrength(int num) {
    num = Validation.validateInteger(num);
    this.strength += num;
  }

  public int getDexterity() {
    return this.dexterity;
  }

  public void setDexterity(int num) {
    num = Validation.validateInteger(num);
    this.dexterity = num;
  }

  public void incrementDexterity(int num) {
    num = Validation.validateInteger(num);
    this.dexterity += num;
  }
}