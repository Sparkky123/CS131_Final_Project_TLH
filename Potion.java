public class Potion extends Item {
    private int healthRestore;

    public Potion(String name, int healthRestore) {
        super(name);
        this.healthRestore = healthRestore;
    }

    public int getHealthRestore() {
        return healthRestore;
    }
}