package mud.player;

import mud.world.Item;
import mud.world.Room;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private Room currentRoom;
    private final List<Item> inventory = new ArrayList<>();

    public Player(Room startRoom) {
        this.currentRoom = startRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public boolean move(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            return true;
        }
        return false;
    }

    public void pickUp(String itemName) {
        if (currentRoom.hasItem(itemName)) {
            inventory.add(currentRoom.removeItem(itemName));
        }
    }

    public List<Item> getInventory() {
        return inventory;
    }
}
