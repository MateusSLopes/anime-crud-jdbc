package anime_store.crud.menu;

import anime_store.crud.service.ProducerService;

import java.util.Scanner;

public class Menu {
    public static Scanner SC = new Scanner(System.in);

    public static void showMenu() {
        System.out.println("1 - Search for producer");
        System.out.println("2 - Create a new producer and save");
        System.out.println("3 - Delete a producer");
        System.out.println("4 - Update a producer");
        System.out.println("0 - Quit");
        int op = Integer.parseInt(SC.nextLine());
        executeOption(op);
    }

    private static void executeOption(int op) {
        switch (op) {
            case 1 -> ProducerService.findByName();
            case 2 -> ProducerService.save();
            case 3 -> ProducerService.delete();
            case 4 -> ProducerService.update();
            case 0 -> System.out.println("Goodbye");
            default -> System.out.println("Invalid option");
        }
    }
}
