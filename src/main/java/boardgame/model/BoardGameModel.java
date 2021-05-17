package boardgame.model;

import javafx.beans.property.ObjectProperty;

import java.util.*;

/**
 *
 */
public class BoardGameModel {

    /**
     *
     */
    public static int BOARD_SIZE = 8;

    /**
     *
     *
     * @return
     */
    public Player isPlayer() {
        return currentPlayer;
    }

    private Player currentPlayer = Player.LIGHT;

    private ArrayList<Position> redSquares = new ArrayList<>();

    private final Piece[] pieces;

    /**
     *
     */
    public BoardGameModel() {
        this(new Piece(PieceType.LIGHT, new Position(0, 0)),
                new Piece(PieceType.DARK, new Position(BOARD_SIZE - 1, BOARD_SIZE - 1)));
    }

    /**
     *
     *
     * @param pieces
     */
    public BoardGameModel(Piece... pieces) {
        checkPieces(pieces);
        this.pieces = pieces.clone();
    }

    private void checkPieces(Piece[] pieces) {
        if (! isOnBoard(pieces[0].getPosition()) || ! isOnBoard(pieces[1].getPosition()) || pieces[0].equals(pieces[1])) {
            throw new IllegalArgumentException();
        }
        getRedSquares().add(pieces[0].getPosition());
        getRedSquares().add(pieces[1].getPosition());
    }

    /**
     *
     *
     * @return
     */
    public int getPieceCount() {
        return pieces.length;
    }

    /**
     *
     *
     * @param pieceNumber
     * @return
     */
    public PieceType getPieceType(int pieceNumber) {
        return pieces[pieceNumber].getType();
    }

    /**
     *
     *
     * @param pieceNumber
     * @return
     */
    public Position getPiecePosition(int pieceNumber) {
        return pieces[pieceNumber].getPosition();
    }

    /**
     *
     *
     * @param pieceNumber
     * @return
     */
    public ObjectProperty<Position> positionProperty(int pieceNumber) {
        return pieces[pieceNumber].positionProperty();
    }

    /**
     *
     *
     * @param pieceNumber
     * @param direction
     * @return
     */
    public boolean isValidMove(int pieceNumber, KnightDirection direction) {
        if (pieceNumber < 0 || pieceNumber >= pieces.length) {
            throw new IllegalArgumentException();
        }
        Position newPosition = pieces[pieceNumber].getPosition().moveTo(direction);
        if (! isOnBoard(newPosition) || getRedSquares().contains(newPosition)) {
            return false;
        }
        for (var piece : pieces) {
            if (piece.getPosition().equals(newPosition)) {
                return false;
            }
        }

        return true;
    }

    /**
     *
     *
     * @param pieceNumber
     * @return
     */
    public Set<KnightDirection> getValidMoves(int pieceNumber) {
        EnumSet<KnightDirection> validMoves = EnumSet.noneOf(KnightDirection.class);
        for (var direction : KnightDirection.values()) {
            if (isValidMove(pieceNumber, direction)) {
                validMoves.add(direction);
            }
        }
        return validMoves;
    }

    /**
     *
     *
     * @param pieceNumber
     * @param direction
     */
    public void move(int pieceNumber, KnightDirection direction) {
        pieces[pieceNumber].moveTo(direction);
        getRedSquares().add(pieces[pieceNumber].getPosition());
        currentPlayer = currentPlayer.alter();
    }

    /**
     *
     *
     * @param position
     * @return
     */
    public static boolean isOnBoard(Position position) {
        return 0 <= position.row() && position.row() < BOARD_SIZE
                && 0 <= position.col() && position.col() < BOARD_SIZE;
    }

    /**
     *
     *
     * @param position
     * @return
     */
    public OptionalInt getPieceNumber(Position position) {
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].getPosition().equals(position)) {
                return OptionalInt.of(i);
            }
        }
        return OptionalInt.empty();
    }

    /**
     *
     *
     * @return
     */
    public boolean winner() {
        if (getValidMoves(currentPlayer == Player.LIGHT ? 1 : 0).isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     *
     *
     * @return
     */
    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (var piece : pieces) {
            joiner.add(piece.toString());
        }
        return joiner.toString();
    }

    /**
     *
     *
     * @return
     */
    public int getPlayer() { return currentPlayer == Player.LIGHT ? 0 : 1; }

    /**
     *
     *
     * @return
     */
    public ArrayList<Position> getRedSquares() {
        return redSquares;
    }

}
