package modelTest;

import boardgame.model.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void alter() {
        assertEquals(Player.LIGHT, Player.DARK.alter());
        assertEquals(Player.DARK, Player.LIGHT.alter());
    }
}
