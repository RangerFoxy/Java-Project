package boardgame.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Elements {

    private String date;

    private String lightKnight;

    private String darkKnight;

    private String winner;

}
