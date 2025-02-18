package mud.world;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private final String name, description;
    private final Map<String, Room> exits = new HashMap<>();
    private final Map<String, Item> items = new HashMap<>();

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void addItem(String name, String description) {
        items.put(name, new Item(name, description));
    }

    public boolean hasItem(String name) {
        return items.containsKey(name);
    }

    public Item removeItem(String name) {
        return items.remove(name);
    }

    public String describe() {
        return name + ": " + description + "\nПредметы: " + (items.isEmpty() ? "нету" : items.keySet());
    }
}
