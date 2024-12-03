public class Player extends Character {
    private ItemContainer<Weapon> weaponInventory;
    private ItemContainer<Potion> potionInventory;
    private Weapon equippedWeapon;

    public Player(String name, int health, int attackPower) {
        super(name, health, attackPower);
        weaponInventory = new ItemContainer<>();
        potionInventory = new ItemContainer<>();
    }

    public void addWeapon(Weapon weapon) {
        weaponInventory.addItem(weapon);
    }

    public void addPotion(Potion potion) {
        potionInventory.addItem(potion);
    }

    public void equipWeapon(Weapon weapon) {
        if (weaponInventory.getItems().contains(weapon)) {
            equippedWeapon = weapon;
            System.out.println(name + " equipped " + weapon.getName() + " with " + weapon.getAttackPower() + " attack power.");
        } else {
            System.out.println("Weapon not in inventory.");
        }
    }

    public void usePotion(Potion potion) {
        if (potionInventory.getItems().contains(potion)) {
            health += potion.getHealthRestore();
            potionInventory.removeItem(potion);
            System.out.println(name + " used " + potion.getName() + " and restored " + potion.getHealthRestore() + " health.");
        } else {
            System.out.println("Potion not in inventory.");
        }
    }
    
    
    public void attack(Character opponent) {
        int totalAttackPower = attackPower + (equippedWeapon != null ? equippedWeapon.getAttackPower() : 0);
        System.out.println(name + " attacks " + opponent.getName() + " for " + totalAttackPower + " damage.");
        opponent.takeDamage(totalAttackPower);
    }
    
    public ItemContainer<Potion> getPotionInventory() {
        return potionInventory;
    }
    
    public ItemContainer<Weapon> getWeaponInventory() {
        return weaponInventory;
    }
}