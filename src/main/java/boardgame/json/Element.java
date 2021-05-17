package boardgame.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class for representing the elements of the scoreboard.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Element {

    private String date;

    private String darkPlayer;

    private String lightPlayer;

    private String winner;

}