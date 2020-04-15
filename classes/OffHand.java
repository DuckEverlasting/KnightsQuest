package KnightsQuest.classes;
import KnightsQuest.enums.OffHandType;

public class OffHand extends Equippable {
  private double accuracy;
  private boolean shield;

  public OffHand(OffHandType type) {
    super(type.name, type.power, type.armor);
    this.accuracy = type.accuracy;
    this.shield = type.shield;
  }

  public OffHand(String name, int power, int armor, double accuracy, boolean shield) {
    super(name, power, armor);
    this.accuracy = accuracy;
    this.shield = shield;
  }

  public double getAccuracy() {
    return this.accuracy;
  }

  public boolean isShield() {
    return this.shield;
  }
}