package anime_store.crud.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Producer {
    private Integer id;
    private String name;
}
