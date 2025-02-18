package mud.controller;

import mud.player.Player;
import mud.world.Item;
import java.util.Scanner;

public class MUDController {
    private final Player player;
    private boolean running = true;

    public MUDController(Player player) {
        this.player = player;
    }

    public void runGameLoop() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Привет!!!, Напишите 'help' для списка команды.");

        while (running) {
            System.out.print("> ");
            handleInput(scanner.nextLine().trim());
        }

        System.out.println("Игра окончено.");
    }

    private void handleInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = (parts.length > 1) ? parts[1] : "";

        switch (command) {
            case "look":
                lookAround();
                break;
            case "move":
                move(argument);
                break;
            case "pick":
                if (argument.startsWith("up ")) {
                    pickUp(argument.substring(3));
                } else {
                    System.out.println("pick up <назв.предмета>");
                }
                break;
            case "inventory":
                checkInventory();
                break;
            case "help":
                showHelp();
                break;
            case "exit":
                running = false;
                break;
            default:
                System.out.println("Ошибка! Напишите 'help'.");
        }
    }

    private void lookAround() {
        System.out.println(player.getCurrentRoom().describe());
    }

    private void move(String direction) {
        if (player.move(direction)) {
            System.out.println("Вы находитесь " + direction + ".");
            lookAround();
        } else {
            System.out.println("Пути нету!!");
        }
    }

    private void pickUp(String itemName) {
        if (itemName.isEmpty()) {
            System.out.println("pick up <назв.предмета>");
        } else if (player.getCurrentRoom().hasItem(itemName)) {
            player.pickUp(itemName);
            System.out.println("Вы забрали " + itemName + ".");
        } else {
            System.out.println("Здесь нет такого предмета.");
        }
    }

    private void checkInventory() {
        if (player.getInventory().isEmpty()) {
            System.out.println("Ваш инвентарь пустой.");
        } else {
            System.out.println("Вы несёте:");
            for (Item item : player.getInventory()) {
                System.out.println("- " + item);
            }
        }
    }

    private void showHelp() {
        System.out.println("Команды:");
        System.out.println("look - Осмотреть комнаты.");
        System.out.println("move <направление> - Переместиться.");
        System.out.println("pick up <назв.предмета> - Забрать предмет.");
        System.out.println("inventory - Посмотреть инвентарь.");
        System.out.println("help - Список команды.");
        System.out.println("exit - Выйти с игры.");
    }
}
