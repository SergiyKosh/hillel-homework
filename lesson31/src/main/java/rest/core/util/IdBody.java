package rest.core.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdBody {
    @JsonProperty
    private String id;
}
