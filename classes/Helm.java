package KnightsQuest.classes;
import KnightsQuest.enums.HelmType;

public class Helm extends Equippable {

  public Helm(HelmType type) {
    super(type.name, type.power, type.armor);
  }

  public Helm(String name, int power, int armor) {
    super(name, power, armor);
  }
}