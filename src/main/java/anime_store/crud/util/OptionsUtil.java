package anime_store.crud.util;

import anime_store.crud.service.AnimeService;
import anime_store.crud.service.ProducerService;

import java.util.Scanner;

public class OptionsUtil {
    public static Scanner SC = new Scanner(System.in);

    public static void showAnimeMenu() {
        System.out.println("1 - Search for anime");
        System.out.println("2 - Create a new anime and save");
        System.out.println("3 - Delete a anime");
        System.out.println("4 - Update a anime");
        System.out.println("0 - Exit");
        int op = Integer.parseInt(SC.nextLine());
        OptionsUtil.executeAnimeService(op);
    }

    public static void showProducerMenu() {
        System.out.println("1 - Search for producer");
        System.out.println("2 - Create a new producer and save");
        System.out.println("3 - Delete a producer");
        System.out.println("4 - Update a producer");
        System.out.println("0 - Exit");
        int op = Integer.parseInt(SC.nextLine());
        OptionsUtil.executeProducerService(op);
    }

    public static void executeAnimeService(int option) {
        switch (option) {
            case 1 -> AnimeService.findByName();
            case 2 -> AnimeService.save();
            case 3 -> AnimeService.delete();
            case 4 -> AnimeService.update();
            case 0 -> System.out.println("Goodbye!");
            default -> System.out.println("Invalid option");
        }
    }

    public static void executeProducerService(int option) {
        switch (option) {
            case 1 -> ProducerService.findByName();
            case 2 -> ProducerService.save();
            case 3 -> ProducerService.delete();
            case 4 -> ProducerService.update();
            case 0 -> System.out.println("Goodbye!");
            default -> System.out.println("Invalid option");
        }
    }
}
