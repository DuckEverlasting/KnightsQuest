package KnightsQuest.classes;
import KnightsQuest.enums.WeaponType;
import KnightsQuest.enums.OffHandType;
import KnightsQuest.enums.ArmorType;
import KnightsQuest.enums.BootsType;
import KnightsQuest.enums.HelmType;

public class Player extends Character {
  private static Player instance = null;
  private Weapon weapon;
  private OffHand offHand;
  private Armor armor;
  private Helm helm;
  private Boots boots;

  private Player() {
    super(100, 10, 10);
    weapon = new Weapon(WeaponType.KNIFE);
    offHand = new OffHand(OffHandType.NONE);
    armor = new Armor(ArmorType.LEATHER);
    helm = new Helm(HelmType.LEATHER);
    boots = new Boots(BootsType.LEATHER);
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