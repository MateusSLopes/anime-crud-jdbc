package anime_store.crud.service;

import anime_store.crud.domain.Producer;
import anime_store.crud.repository.ProducerRepository;

import java.util.Optional;
import java.util.Scanner;


public class ProducerService {
    public static Scanner SC = new Scanner(System.in);

    public static void delete() {
        System.out.printf("Type the producer name to delete: ");
        showProducersWithId(SC.nextLine());
        System.out.printf("Type the producer id to delete: ");
        ProducerRepository.delete(Integer.parseInt(SC.nextLine()));
    }

    public static void findByName() {
        System.out.printf("Type the producer name or empty to all: ");
        showProducersByName(SC.nextLine());
    }

    public static void save() {
        System.out.printf("Type the producer name to create and save in the database: ");
        ProducerRepository.save(Producer.builder().name(SC.nextLine()).build());
        System.out.println("Successfully saved the producer");
    }

    public static void update() {
        showProducersWithId("");
        System.out.printf("Type the producer ID to update: ");
        Optional<Producer> producerOptional = ProducerRepository.findById(Integer.parseInt(SC.nextLine()));
        if (producerOptional.isEmpty()) {
            System.out.println("Producer not found");
            return;
        }
        var producerFromDb = producerOptional.get();
        System.out.printf("Type the new producer name: ");
        String newName = SC.nextLine();
        newName = newName.isEmpty() ? producerFromDb.getName() : newName;
        var producerToUpdate = Producer.builder()
                .id(producerFromDb.getId())
                .name(newName).build();
        ProducerRepository.update(producerToUpdate);
    }

    private static void showProducersByName(String name) {
        var listProducers = ProducerRepository.findByName(name);
        for (int i = 0; i < listProducers.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, listProducers.get(i).getName());
        }
    }

    private static void showProducersWithId(String name) {
        var listProducers = ProducerRepository.findByName(name);
        for (int i = 0; i < listProducers.size(); i++) {
            System.out.printf("ID: %d - %s\n", listProducers.get(i).getId(), listProducers.get(i).getName());
        }
    }
}
