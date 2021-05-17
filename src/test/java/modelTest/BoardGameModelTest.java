package modelTest;

import boardgame.model.*;
import java.util.EnumSet;
import java.util.OptionalInt;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardGameModelTest {

    BoardGameModel boardgameModel = new BoardGameModel();

    @Test
    void testGetPieceNumber() {
       Piece piece = new Piece(PieceType.LIGHT, new Position(0, 0));
       assertEquals(boardgameModel.getPieceNumber(piece.getPosition()), OptionalInt.of(0));
    }

    @Test
    void testCheckPiece() {
        assertThrows(IllegalArgumentException.class, () -> new BoardGameModel(
                new Piece(PieceType.LIGHT, new Position(10, 10)),
                new Piece(PieceType.DARK, new Position(10, 10)),
                new Piece(PieceType.DARK, new Position(-10, -10))));
    }

    @Test
    void testIsValidMove() {
        BoardGameModel boardgame = new BoardGameModel(
                new Piece(PieceType.LIGHT, new Position(0, 0)),
                new Piece(PieceType.DARK, new Position(2, 1)));
        assertThrows(IllegalArgumentException.class, () -> boardgame.isValidMove(-1, KnightDirection.RIGHT_DOWN));
        assertFalse(boardgame.isValidMove(0, KnightDirection.DOWN_RIGHT));
        assertFalse(boardgame.isValidMove(1, KnightDirection.UP_LEFT));
        assertTrue(boardgame.isValidMove(1, KnightDirection.RIGHT_DOWN));
    }

    @Test
    void testMove() {
        boardgameModel.move(0, KnightDirection.UP_LEFT);
        boardgameModel.move(0, KnightDirection.DOWN_LEFT);
        boardgameModel.move(1, KnightDirection.DOWN_LEFT);
        boardgameModel.move(1, KnightDirection.UP_LEFT);
    }

    @Test
    void testGetValidMoves() {
        BoardGameModel boardgame = new BoardGameModel(
                new Piece(PieceType.LIGHT, new Position(0, 0)),
                new Piece(PieceType.DARK, new Position(2, 1)));
        assertEquals(boardgame.getValidMoves(1), EnumSet.of(
                KnightDirection.UP_RIGHT,
                KnightDirection.RIGHT_UP,
                KnightDirection.RIGHT_DOWN,
                KnightDirection.DOWN_RIGHT,
                KnightDirection.DOWN_LEFT
        ));
    }

    @Test
    void testToString() {
        assertEquals(boardgameModel.toString(), "[LIGHT(0,0),DARK(7,7)]");
    }

    @Test
    void testGetPieceCount() {
        assertEquals(boardgameModel.getPieceCount(), 2);
    }

    @Test
    void testGetPieceType() {
        assertEquals(boardgameModel.getPieceType(0), PieceType.LIGHT);
        assertEquals(boardgameModel.getPieceType(1), PieceType.DARK);
    }

    @Test
    void testGetPlayer() {
        assertEquals(boardgameModel.getPlayer(), 0);
        boardgameModel.move(0, KnightDirection.DOWN_LEFT);
        assertEquals(boardgameModel.getPlayer(), 1);
    }

    @Test
    void testIsPlayer() {
        assertEquals(boardgameModel.isPlayer(), Player.LIGHT);
    }

    @Test
    void testGetPiecePosition() {
        assertEquals(boardgameModel.getPiecePosition(0), new Position(0, 0));
        assertEquals(boardgameModel.getPiecePosition(1), new Position(7, 7));
    }

    @Test
    void testIsOnBoard() {
        assertFalse(boardgameModel.isOnBoard(new Position(10, 0)));
        assertFalse(boardgameModel.isOnBoard(new Position(0, 10)));
        assertFalse(boardgameModel.isOnBoard(new Position(-10, 0)));
        assertFalse(boardgameModel.isOnBoard(new Position(0, -10)));
        assertTrue(boardgameModel.isOnBoard(new Position(4, 4)));
        assertTrue(boardgameModel.isOnBoard(new Position(0, 0)));
    }

    @Test
    void testWinner() {
        boardgameModel.move(0, KnightDirection.DOWN_RIGHT);
        boardgameModel.move(1, KnightDirection.LEFT_UP);
        assertFalse(boardgameModel.winner());
        boardgameModel.move(0, KnightDirection.DOWN_LEFT);
        boardgameModel.move(1, KnightDirection.LEFT_DOWN);
        boardgameModel.move(0, KnightDirection.DOWN_RIGHT);
        boardgameModel.move(1, KnightDirection.UP_LEFT);
        boardgameModel.move(0, KnightDirection.RIGHT_UP);
        boardgameModel.move(1, KnightDirection.DOWN_LEFT);
        boardgameModel.move(0, KnightDirection.DOWN_LEFT);
        boardgameModel.move(1, KnightDirection.UP_LEFT);
        boardgameModel.move(0, KnightDirection.UP_LEFT);
        boardgameModel.move(1, KnightDirection.RIGHT_DOWN);
        boardgameModel.move(0, KnightDirection.RIGHT_DOWN);
        boardgameModel.move(1, KnightDirection.LEFT_DOWN);
        assertTrue(boardgameModel.winner());
    }

}
