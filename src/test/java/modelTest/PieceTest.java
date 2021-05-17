package modelTest;

import boardgame.model.Piece;
import boardgame.model.Position;
import boardgame.model.PieceType;
import org.junit.jupiter.api.Test;
import boardgame.model.KnightDirection;
import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    @Test
    void testPiece() {
        Piece piece = new Piece(PieceType.LIGHT, new Position(0, 0));
        assertEquals(piece.getType(), PieceType.LIGHT);
        assertEquals(piece.getPosition(), new Position(0, 0));
        piece.moveTo(KnightDirection.LEFT_UP);
        assertEquals(piece.getPosition(), new Position(-1, -2));
    }

    @Test
    void testToString() {
        Piece piece = new Piece(PieceType.LIGHT, new Position(0,0));
        assertEquals(piece.toString(), "LIGHT(0,0)");
    }

}
