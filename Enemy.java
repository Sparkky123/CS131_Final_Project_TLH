public class Enemy extends Character {
    private int maxHealth;

    public Enemy(String name, int health, int attackPower) {
        super(name, health, attackPower);
        this.maxHealth = health; // Store the maximum health
    }

    public void resetHealth() {
        setHealth(maxHealth);
    }
}