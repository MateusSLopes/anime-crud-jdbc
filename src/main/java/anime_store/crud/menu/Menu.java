package anime_store.crud.menu;

import anime_store.crud.domain.Producer;
import anime_store.crud.service.ProducerService;

import java.util.Scanner;

public class Menu {
    public static Scanner sc = new Scanner(System.in);

    public static void showMenu() {
        System.out.println("1 - Search for producer");
        System.out.println("2 - Create a new producer and save");
        System.out.println("3 - Delete a producer");
        System.out.println("4 - Update a producer");
        System.out.println("0 - Quit");
        int op = Integer.parseInt(sc.nextLine());
        executeOption(op);
    }

    private static void executeOption(int op) {
        switch (op) {
            case 1:
                System.out.printf("Type the producer name or empty to all: ");
                String nameToFind = sc.nextLine();
                showProducersByName(nameToFind);
                break;
            case 2:
                System.out.printf("Type the producer name to create and save in the database: ");
                String nameToCreate = sc.nextLine();
                ProducerService.save(Producer.builder().name(nameToCreate).build());
                System.out.println("Successfully saved the producer " + nameToCreate);
                break;
            case 3:
                System.out.printf("Type the producer name to delete: ");
                String nameToDelete = sc.nextLine();
                showProducersWithId(nameToDelete);
                System.out.printf("Type the producer ID to delete: ");
                ProducerService.delete(Integer.parseInt(sc.nextLine()));
                break;
            case 4:
                showProducersWithId("");
                System.out.printf("Type the producer ID to update: ");
                Integer id = Integer.parseInt(sc.nextLine());
                System.out.printf("Type the new producer name: ");
                String newName = sc.nextLine();
                ProducerService.update(Producer.builder().id(id).name(newName).build());
                break;
            case 0:
                System.out.println("Goodbye!");
                break;
            default:
                throw new IllegalArgumentException("Invalid option");
        }
    }

    private static void showProducersByName(String name) {
        var listProducers = ProducerService.findByName(name);
        for (int i = 0; i < listProducers.size(); i++) {
            System.out.printf("%d - %s\n", i+1, listProducers.get(i).getName());
        }
    }

    private static void showProducersWithId(String name) {
        var listProducers = ProducerService.findByName(name);
        for (int i = 0; i < listProducers.size(); i++) {
            System.out.printf("ID: %d - %s\n", listProducers.get(i).getId(), listProducers.get(i).getName());
        }
    }
}
