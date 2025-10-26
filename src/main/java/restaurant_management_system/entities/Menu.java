package restaurant_management_system.entities;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private final Map<String, MenuItem> items = new HashMap<>();
    public void addItem(MenuItem item) {
        items.put(item.getId(), item);
    }

    public MenuItem getItem(String id) {
        MenuItem item = items.get(id);
        if(item == null) {
            throw new IllegalArgumentException("Menu Item with id: "+id+" not found int the menu");
        }
        return item;
    }
}
