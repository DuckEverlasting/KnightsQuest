package KnightsQuest.classes;
import KnightsQuest.enums.WeaponType;

public class Weapon extends Equippable {
  private double accuracy;
  private boolean twoHanded;

  public Weapon(WeaponType type) {
    super(type.name, type.power, type.armor);
    this.accuracy = type.accuracy;
    this.twoHanded = type.twoHanded;
  }

  public Weapon(String name, int power, int armor, double accuracy, boolean twoHanded) {
    super(name, power, armor);
    this.accuracy = accuracy;
    this.twoHanded = twoHanded;
  }

  public double getAccuracy() {
    return this.accuracy;
  }

  public boolean isTwoHanded() {
    return this.twoHanded;
  }
}