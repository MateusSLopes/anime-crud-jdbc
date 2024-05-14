package anime_store.crud.test;

import anime_store.crud.service.AnimeService;
import anime_store.crud.service.ProducerService;

import java.util.Scanner;

public class CrudTest01 {
    public static void main(String[] args) {
        mainMenu();
    }
    private static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Type the number of your operation");
            System.out.println("1 - Producer");
            System.out.println("2 - Anime");
            System.out.println("0 - Exit");
            int op = Integer.parseInt(sc.nextLine());
            if (op == 0) break;
            switch (op) {
                case 1 -> ProducerService.showMenu();
                case 2 -> AnimeService.showMenu();
                default -> System.out.println("Invalid option");
            }
        }
    }
}
