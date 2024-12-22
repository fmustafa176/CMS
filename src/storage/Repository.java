package storage;

import java.util.ArrayList;
import java.util.List;
import classes.GetIdentity;

public class Repository<T extends GetIdentity> {
    private ArrayList<T> items;

    public Repository() {
        this.items = new ArrayList<>();
    }

    public String add(T item) {
        items.add(item);
        University.saveData();
        return item.getClass().getSimpleName() + " " + item.getID() + " added to the repository.";
    }

    public String remove(T item) {
        if (items.remove(item)) {
            University.saveData();
            return item.getClass().getSimpleName() + " removed from the repository.";
        } else {
            return "Error: " + item.getClass().getSimpleName() + " " + item.getID() + " not found in the repository.";
        }
    }

    public List<T> getAll() {
        return items;
    }
}
