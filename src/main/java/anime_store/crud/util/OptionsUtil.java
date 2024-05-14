package anime_store.crud.util;

import anime_store.crud.service.AnimeService;
import anime_store.crud.service.ProducerService;

public class OptionsUtil {
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
