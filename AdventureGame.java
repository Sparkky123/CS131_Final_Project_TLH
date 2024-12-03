
/**
 * AdventureGame class represents the main game logic for an adventure game.
 * Players navigate between rooms, fight enemies, collect treasures, and aim to defeat the final boss.
 * 
 * @author Tristan Hibbard
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class AdventureGame {
    private Player player;
    private Room safeRoom;
    private Room dangerousRoom;
    private Boss finalBoss;
    private int dangerousRoomCount;
    private int safeRoomCount;
    private Random random;
    private List<Enemy> enemies;

    /**
     * Constructs the AdventureGame and initializes the rooms, enemies, and player stats.
     */
    public AdventureGame() {
        safeRoom = new Room("Safe Room", false);
        dangerousRoom = new Room("Dangerous Room", true);
        finalBoss = new Boss("Final Boss", 100, 20);
        dangerousRoomCount = 0;
        safeRoomCount = 0;
        random = new Random();
        
        enemies = new ArrayList<>();
        enemies.add(new Enemy("Goblin", 50, 10));
        enemies.add(new Enemy("Orc", 60, 15));
        enemies.add(new Enemy("Troll", 80, 20));
        enemies.add(new Enemy("Dark Knight", 90, 25));
    } // end AdventureGame constructor

    /**
     * Starts the game loop, allowing the player to navigate between rooms and interact with the game.
     */
    public void start() {
        Scanner scnr = new Scanner(System.in);

        System.out.println("Enter your character's name:");
        String name = scnr.nextLine();
        player = new Player(name, 100, 15);
        
        Weapon sword = new Weapon("Sword", 20);
        Potion healthPotion = new Potion("Health Potion", 50);

        player.addWeapon(sword);
        player.addPotion(healthPotion);

        player.equipWeapon(sword);

        boolean gameOn = true;
        while (gameOn) {
            try {
                System.out.println("Choose a room to enter (1 for Safe Room, 2 for Dangerous Room):");
                int choice = scnr.nextInt();
                
                if (choice != 1 && choice != 2) {
                    throw new IllegalArgumentException("Invalid room choice. Please enter 1 or 2.");
                }

                Room selectedRoom = (choice == 1) ? safeRoom : dangerousRoom;
                enterRoom(selectedRoom);
                
                
                if (checkBossRoomConditions()) {
                    System.out.println("You've fulfilled the conditions to reach the boss room! Prepare for the final fight.");
                    fight(finalBoss);
                    gameOn = false;
                } else {
                    System.out.println("Do you want to continue to another room? (y/n):");

                    String continueChoice = scnr.next();
                    if (!continueChoice.equalsIgnoreCase("y") && !continueChoice.equalsIgnoreCase("n")) {
                        throw new IllegalArgumentException("Invalid choice. Please enter 'y' or 'n'.");
                    }
                    if (!continueChoice.equalsIgnoreCase("y")) {
                        System.out.println("You chose to stop the game. Goodbye!");
                        gameOn = false;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scnr.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                scnr.nextLine();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                gameOn = false;
            }
        }
    } // end start

    /**
     * Enters the specified room and handles room-specific actions.
     * 
     * @param room the room the player enters
     */
    private void enterRoom(Room room) {
        System.out.println("Entering " + room.getName() + "...");
        if (room.isDangerous()) {
            dangerousRoomCount++;
            
            Enemy enemy = enemies.get(random.nextInt(enemies.size()));
            enemy.resetHealth();
            
            System.out.println("An enemy appears! It's a " + enemy.getName() + "!");
            fight(enemy);
        } else {
            System.out.println("It's safe here. You can rest or use a potion if needed.");
            if (!player.getPotionInventory().isEmpty()) {
                player.usePotion(player.getPotionInventory().getItems().get(0));
            }
            safeRoomCount++;
        }
    } // end enterRoom

    /**
     * Conducts a fight between the player and the specified opponent.
     * 
     * @param opponent the opponent to fight
     */
    private void fight(Character opponent) {
        while (player.isAlive() && opponent.isAlive()) {
            player.attack(opponent);
            if (opponent.isAlive()) {
                opponent.attack(player);
            }
        }

        if (player.isAlive()) {
            System.out.println(player.getName() + " defeated " + opponent.getName() + "!");
            awardTreasure();
        } else {
            System.out.println(player.getName() + " was defeated. Game Over.");
            System.exit(0);
        }
    } // end fight

    /**
     * Awards the player a random treasure after defeating an opponent.
     */
    private void awardTreasure() {
        List<Treasure> treasures = new ArrayList<>();
        treasures.add(new Treasure("Gold Coins", random.nextInt(50) + 50));
        treasures.add(new Treasure("Gemstone", random.nextInt(100) + 100));
        treasures.add(new Treasure("Rare Artifact", random.nextInt(200) + 200));
        treasures.add(new Treasure("Special Healing Potion", 1));
        
        Treasure treasure = treasures.get(random.nextInt(treasures.size()));
        System.out.println("You found a treasure: " + treasure.getName() + "!");
        
        if (treasure.getName().equals("Special Healing Potion")) {
            player.addPotion(new Potion("Special Healing Potion", 100));
        } else {
            System.out.println("Treasure value: " + treasure.getValue() + " gold.");
        }
    } // end awardTreasure

    /**
     * Checks if the conditions to reach the boss room have been met.
     * 
     * @return true if the boss room conditions are met, false otherwise
     */
    private boolean checkBossRoomConditions() {
        return (dangerousRoomCount == 6) ||
               (safeRoomCount == 12) ||
               (dangerousRoomCount == 5 && safeRoomCount >= 2) ||
               (dangerousRoomCount == 4 && safeRoomCount >= 4) ||
               (dangerousRoomCount == 3 && safeRoomCount >= 6) ||
               (dangerousRoomCount == 2 && safeRoomCount >= 8);
    } // end checkBossRoomConditions

    /**
     * Main method to run the game.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        AdventureGame game = new AdventureGame();
        game.start();
    } // end main
}