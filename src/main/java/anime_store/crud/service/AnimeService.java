package anime_store.crud.service;

import anime_store.crud.domain.Anime;
import anime_store.crud.domain.Producer;
import anime_store.crud.repository.AnimeRepository;

import java.util.Optional;
import java.util.Scanner;


public class AnimeService {
    private static Scanner SC = new Scanner(System.in);

    public static void delete() {
        System.out.printf("Type the anime id to delete: ");
        AnimeRepository.delete(Integer.parseInt(SC.nextLine()));
    }

    public static void findByName() {
        System.out.printf("Type the anime name or empty to all: ");
        String name = SC.nextLine();
        AnimeRepository.findByName(name)
                .forEach(p -> System.out.printf("ID: %d - %s, %d episodes, Producer ID: %d\n", p.getId(), p.getName(), p.getEpisodes(), p.getProducer().getId()));
    }

    public static void save() {
        System.out.printf("Type the anime name to create and save in the database: ");
        String name = SC.nextLine();
        System.out.printf("Type the episodes number: ");
        int episodes = Integer.parseInt(SC.nextLine());
        System.out.printf("Type the producer ID: ");
        Integer producerId = Integer.parseInt(SC.nextLine());
        ;
        var anime = Anime.builder()
                .name(name)
                .episodes(episodes)
                .producer(Producer.builder().id(producerId).build())
                .build();
        AnimeRepository.save(anime);
        System.out.println("Successfully saved the anime");
    }

    public static void update() {
        System.out.printf("Type the anime ID to update: ");
        Optional<Anime> animeOptional = AnimeRepository.findById(Integer.parseInt(SC.nextLine()));
        if (animeOptional.isEmpty()) {
            System.out.println("Anime not found");
            return;
        }
        var animeFromDb = animeOptional.get();
        System.out.printf("Type the new anime name: ");
        String newName = SC.nextLine();
        newName = newName.isEmpty() ? animeFromDb.getName() : newName;
        System.out.printf("Type the new number of episodes: ");
        int episodes = Integer.parseInt(SC.nextLine());

        var animeToUpdate = Anime.builder()
                .id(animeFromDb.getId())
                .episodes(episodes)
                .name(newName)
                .build();
        AnimeRepository.update(animeToUpdate);
    }
}
