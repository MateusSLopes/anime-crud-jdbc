package anime_store.crud.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Anime {
    private Integer id;
    private String name;
    private int episodes;
    private Producer producer;
}
