package classes;

public class Equippable {
  private String name;
  private int power;
  private int armor;

  public Equippable(String name, int power, int armor) {
    this.name = name;
    this.power = power;
    this.armor = armor;
  }

  public String getName() {
    return this.name;
  }

  public int getPower() {
    return this.power;
  }

  public int getArmor() {
    return this.armor;
  }
}