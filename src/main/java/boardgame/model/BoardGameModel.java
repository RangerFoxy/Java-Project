package boardgame.model;

import javafx.beans.property.ObjectProperty;

import java.util.*;

/**
 *
 */
public class BoardGameModel {

    /**
     * The number of rows and columns of the board.
     */
    public static int BOARD_SIZE = 8;

    private Player currentPlayer = Player.LIGHT;

    private ArrayList<Position> redSquares = new ArrayList<>();

    private final Piece[] pieces;

    /**
     * Initailizes the game with a dark and a light pieces with their initial places.
     */
    public BoardGameModel() {
        this(new Piece(PieceType.LIGHT, new Position(0, 0)),
                new Piece(PieceType.DARK, new Position(BOARD_SIZE - 1, BOARD_SIZE - 1)));
    }

    /**
     * Creates the game with the given {@code pieces} pieces.
     *
     * @param pieces the list of the pieces
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
     * Returns with the length of the {@code pieces} list (with the number of pieces).
     *
     * @return the length of the pieces list
     */
    public int getPieceCount() {
        return pieces.length;
    }

    /**
     * Returns the type of the given {@code pieceNumber} numbered piece. It's equal with the player's number.
     *
     * @param pieceNumber the number of the piece (equal with the number of the player)
     * @return the type of the piece
     */
    public PieceType getPieceType(int pieceNumber) {
        return pieces[pieceNumber].getType();
    }

    /**
     * Returns the position of the piece from the {@code pieces} array.
     *
     * @param pieceNumber an {@link Integer} representing the piece
     * @return the position of the piece
     */
    public Position getPiecePosition(int pieceNumber) {
        return pieces[pieceNumber].getPosition();
    }

    /**
     * Returns the position of the piece as an ObjectProperty from the {@code pieces} array.
     *
     * @param pieceNumber an {@link Integer} representing the piece
     * @return the position of the piece
     */
    public ObjectProperty<Position> positionProperty(int pieceNumber) {
        return pieces[pieceNumber].positionProperty();
    }

    /**
     * Checks if a move with the piece given by the {@code pieceNumber} is possible or not.
     *
     * @param pieceNumber the piece represented as an {@link Integer}
     * @param direction the direction of the move
     * @return if the move is possible or not
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
     * Checks the {@code KnightDirection} directions and stores every possible move.
     *
     * @param pieceNumber the piece represented as an {@link Integer}
     * @return the {@code validMoves} which is an {@link EnumSet}
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
     * Returns the current player as a {@code Player}.
     *
     * @return the current player
     */
    public Player isPlayer() {
        return currentPlayer;
    }

    /**
     * Moves the piece from the current position to the new position and switches the {@code currentPlayer}.
     *
     * @param pieceNumber the piece represented as an {@link Integer}
     * @param direction the direction of the move as a {@code KnightDirection}
     */
    public void move(int pieceNumber, KnightDirection direction) {
        pieces[pieceNumber].moveTo(direction);
        getRedSquares().add(pieces[pieceNumber].getPosition());
        currentPlayer = currentPlayer.alter();
    }

    /**
     * Checks if the given {@code position} position is on the board or not.
     *
     * @param position the position which is needed to be checked
     * @return if the given {@code position} is on the board or not
     */
    public static boolean isOnBoard(Position position) {
        return 0 <= position.row() && position.row() < BOARD_SIZE
                && 0 <= position.col() && position.col() < BOARD_SIZE;
    }

    /**
     * Returns the piece's number, if on the given {@code Position} position there is a piece, otherwise it returns
     * an empty {@link OptionalInt}.
     *
     * @param position the piece's position
     * @return the number of the piece which is in the given {@code Position} position
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
     * Returns if the dark or light player has no more valid move.
     *
     * @return if a player has no move
     */
    public boolean winner() {
        return getValidMoves(currentPlayer == Player.LIGHT ? 1 : 0).isEmpty();
    }

    /**
     * Return the {@link String} representation of the {@code BoardGameModel} object.
     *
     * @return a {@link String} representing the object
     */
    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (var piece : pieces) {
            joiner.add(piece.toString());
        }
        return joiner.toString();
    }

    /**
     * Returns 0 if the {@code currentPlayer} is equal with {@code Player.LIGHT}, otherwise return with 1.
     *
     * @return if it's the light or the dark player's turn
     */
    public int getPlayer() { return currentPlayer == Player.LIGHT ? 0 : 1; }

    /**
     * Returns with the {@code redSquares} {@link ArrayList} (with the inactive tiles in it).
     *
     * @return the {@code redSquares} {@link ArrayList}
     */
    public ArrayList<Position> getRedSquares() {
        return redSquares;
    }

}
