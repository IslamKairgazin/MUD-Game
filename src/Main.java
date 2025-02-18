package mud;

import mud.controller.MUDController;
import mud.player.Player;
import mud.world.Room;

public class Main {
    public static void main(String[] args) {
        Room startRoom = new Room("Комната старта", "Большая комната.");
        Room hallway = new Room("Коридор", "Длинный.");

        startRoom.setExit("forward", hallway);
        hallway.setExit("back", startRoom);

        startRoom.addItem("AK-47", "заряженный AK-47.");
        startRoom.addItem("Нож", "острый нож.");

        new MUDController(new Player(startRoom)).runGameLoop();
    }
}
