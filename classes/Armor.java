package KnightsQuest.classes;
import KnightsQuest.enums.ArmorType;

public class Armor extends Equippable {

  public Armor(ArmorType type) {
    super(type.name, type.power, type.armor);
  }

  public Armor(String name, int power, int armor) {
    super(name, power, armor);
  }
}