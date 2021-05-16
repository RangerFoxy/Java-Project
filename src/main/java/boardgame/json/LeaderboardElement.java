package boardgame.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaderboardElement {

    private String date;

    private String darkPlayer;

    private String lightPlayer;

    private String winner;

}