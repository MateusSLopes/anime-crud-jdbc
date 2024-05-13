package anime_store.crud.service;

import anime_store.crud.domain.Producer;
import anime_store.crud.repository.ProducerRepository;

import java.util.List;

public class ProducerService {
    public static void delete(Integer id) {
        ProducerRepository.delete(id);
    }

    public static List<Producer> findByName(String name) {
        return ProducerRepository.findByName(name);
    }

    public static void save(Producer producer) {
        ProducerRepository.save(producer);
    }

    public static void update(Producer producer) {
        ProducerRepository.update(producer);
    }
}
