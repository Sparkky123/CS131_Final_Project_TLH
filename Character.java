public abstract class Character {
    protected String name;
    protected int health;
    protected int attackPower;

    public Character(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public void attack(Character opponent) {
        System.out.println(name + " attacks " + opponent.getName() + " for " + attackPower + " damage.");
        opponent.takeDamage(attackPower);
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " has " + health + " health left.");
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
}