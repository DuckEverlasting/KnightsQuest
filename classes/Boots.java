package KnightsQuest.classes;
import KnightsQuest.enums.BootsType;

public class Boots extends Equippable {

  public Boots(BootsType type) {
    super(type.name, type.power, type.armor);
  }

  public Boots(String name, int power, int armor) {
    super(name, power, armor);
  }
}