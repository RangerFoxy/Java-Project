package modelTest;

import org.junit.jupiter.api.Test;
import boardgame.model.KnightDirection;
import static org.junit.jupiter.api.Assertions.*;

public class KnightDirectionTest {

    @Test
    void knightMove() {
        assertEquals(KnightDirection.LEFT_UP, KnightDirection.of(-1, -2));
        assertEquals(KnightDirection.UP_LEFT, KnightDirection.of(-2, -1));
        assertEquals(KnightDirection.UP_RIGHT, KnightDirection.of(-2, 1));
        assertEquals(KnightDirection.RIGHT_UP, KnightDirection.of(-1, 2));
        assertEquals(KnightDirection.RIGHT_DOWN, KnightDirection.of(1, 2));
        assertEquals(KnightDirection.DOWN_RIGHT, KnightDirection.of(2,1));
        assertEquals(KnightDirection.DOWN_LEFT, KnightDirection.of(2, -1));
        assertEquals(KnightDirection.LEFT_DOWN, KnightDirection.of(1, -2));

        assertThrows(IllegalArgumentException.class, () -> KnightDirection.of(3,5));
        assertThrows(IllegalArgumentException.class, () -> KnightDirection.of(8, 0));
    }

}
