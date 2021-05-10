package boardgame.model;

import javafx.beans.property.ObjectProperty;

import java.util.*;

public class BoardGameModel {

    public static int BOARD_SIZE = 8;

    private boolean player = true;

    public ArrayList<Position> redSquares = new ArrayList<>();

    private final Piece[] pieces;

    public BoardGameModel() {
        this(new Piece(PieceType.LIGHT, new Position(0, 0)),
                new Piece(PieceType.DARK, new Position(BOARD_SIZE - 1, BOARD_SIZE - 1)));
    }

    public BoardGameModel(Piece... pieces) {
        checkPieces(pieces);
        this.pieces = pieces.clone();
    }

    private void checkPieces(Piece[] pieces) {
        if (! isOnBoard(pieces[0].getPosition()) || ! isOnBoard(pieces[1].getPosition()) || pieces[0].equals(pieces[1])) {
            throw new IllegalArgumentException();
        }
        redSquares.add(pieces[0].getPosition());
        redSquares.add(pieces[1].getPosition());
    }

    public int getPieceCount() {
        return pieces.length;
    }

    public PieceType getPieceType(int pieceNumber) {
        return pieces[pieceNumber].getType();
    }

    public Position getPiecePosition(int pieceNumber) {
        return pieces[pieceNumber].getPosition();
    }

    public ObjectProperty<Position> positionProperty(int pieceNumber) {
        return pieces[pieceNumber].positionProperty();
    }

    public boolean isValidMove(int pieceNumber, KnightDirection direction) {
        if (pieceNumber < 0 || pieceNumber >= pieces.length) {
            throw new IllegalArgumentException();
        }
        Position newPosition = pieces[pieceNumber].getPosition().moveTo(direction);
        if (! isOnBoard(newPosition) || redSquares.contains(newPosition)) {
            return false;
        }
        for (var piece : pieces) {
            if (piece.getPosition().equals(newPosition)) {
                return false;
            }
        }
        return true;
    }

    public Set<KnightDirection> getValidMoves(int pieceNumber) {
        EnumSet<KnightDirection> validMoves = EnumSet.noneOf(KnightDirection.class);
        for (var direction : KnightDirection.values()) {
            if (isValidMove(pieceNumber, direction)) {
                validMoves.add(direction);
            }
        }
        return validMoves;
    }

    public void move(int pieceNumber, KnightDirection direction) {
        pieces[pieceNumber].moveTo(direction);
        redSquares.add(pieces[pieceNumber].getPosition());
        player = !player;
    }

    public static boolean isOnBoard(Position position) {
        return 0 <= position.row() && position.row() < BOARD_SIZE
                && 0 <= position.col() && position.col() < BOARD_SIZE;
    }

    public List<Position> getPiecePositions() {
        List<Position> positions = new ArrayList<>(pieces.length);
        for (var piece : pieces) {
            positions.add(piece.getPosition());
        }
        return positions;
    }

    public OptionalInt getPieceNumber(Position position) {
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i].getPosition().equals(position)) {
                return OptionalInt.of(i);
            }
        }
        return OptionalInt.empty();
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (var piece : pieces) {
            joiner.add(piece.toString());
        }
        return joiner.toString();
    }

    public int getPlayer() { return player ? 0 : 1; }

//    public static void main(String[] args) {
//        BoardGameModel model = new BoardGameModel();
//        System.out.println(model);
//    }

}
