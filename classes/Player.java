package KnightsQuest.classes;

import KnightsQuest.enums.Weapons;

public class Player extends Character {
  private static Player instance = null;
  private Weapon weapon;
  private OffHand offHand;
  private Armor armor;
  private Helm helm;
  private Boots boots;

  private Player() {
    super(100, 10, 10);
    Weapons axe = Weapons.AXE;
    weapon = new Weapon(axe.name, axe.power, axe.accuracy, axe.twoHanded);
  }

  public static Player getPlayer() {
    if (instance == null) {
      instance = new Player();
    }
    return instance;
  }

  public Weapon getWeapon() {
    return this.weapon;
  }

  public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }
  
  public OffHand getOffHand() {
    return this.offHand;
  }

  public void setOffHand(OffHand offHand) {
    this.offHand = offHand;
  }

  public Boots getBoots() {
    return this.boots;
  }

  public void setBoots(Boots boots) {
    this.boots = boots;
  }

  public Armor getArmor() {
    return this.armor;
  }

  public void setArmor(Armor armor) {
    this.armor = armor;
  }

  public Helm getHelm() {
    return this.helm;
  }

  public void setHelm(Helm helm) {
    this.helm = helm;
  }
}