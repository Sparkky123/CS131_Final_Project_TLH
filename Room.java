public class Room {
    private String name;
    private boolean isDangerous;

    public Room(String name, boolean isDangerous) {
        this.name = name;
        this.isDangerous = isDangerous;
    }

    public String getName() {
        return name;
    }

    public boolean isDangerous() {
        return isDangerous;
    }
}