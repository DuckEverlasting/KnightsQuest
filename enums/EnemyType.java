package enums;

public enum EnemyType {
  GOBLIN("Goblin", 50, 6, 8),
  PIRATE("Pirate", 100, 12, 12),
  DINOSAUR("Dinosaur", 200, 16, 8),
  NINJA("Ninja", 80, 10, 20),
  ZOMBIE("Zombie", 150, 12, 3);

  public String name;
  public int health, strength, dexterity;

  EnemyType(String name, int health, int strength, int dexterity) {
    this.name = name;
    this.health = health;
    this.strength = strength;
    this.dexterity = dexterity;
  }
}