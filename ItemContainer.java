import java.util.ArrayList;
import java.util.List;

public class ItemContainer<T extends Item> {
    private List<T> items = new ArrayList<>();

    public void addItem(T item) {
        items.add(item);
        System.out.println(item.getName() + " added to inventory.");
    }

    public void removeItem(T item) {
        items.remove(item);
        System.out.println(item.getName() + " removed from inventory.");
    }

    public List<T> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}