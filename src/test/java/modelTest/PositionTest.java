package modelTest;

import boardgame.model.KnightDirection;
import boardgame.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    Position position;

    @BeforeEach
    void init() {
        position = new Position(0, 0);
    }

    @Test
    void moving() {
        assertEquals(position.moveTo(KnightDirection.LEFT_UP), new Position(-1, -2));
        assertEquals(position.moveTo(KnightDirection.UP_LEFT), new Position(-2, -1));
        assertEquals(position.moveTo(KnightDirection.UP_RIGHT), new Position(-2, 1));
        assertEquals(position.moveTo(KnightDirection.RIGHT_UP), new Position(-1, 2));
        assertEquals(position.moveTo(KnightDirection.RIGHT_DOWN), new Position(1, 2));
        assertEquals(position.moveTo(KnightDirection.DOWN_RIGHT), new Position(2, 1));
        assertEquals(position.moveTo(KnightDirection.DOWN_LEFT), new Position(2, -1));
        assertEquals(position.moveTo(KnightDirection.LEFT_DOWN), new Position(1, -2));
    }

}
