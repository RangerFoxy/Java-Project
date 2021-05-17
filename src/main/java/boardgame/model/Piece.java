package boardgame.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * A Class for representing pieces.
 */
public class Piece {

    private final PieceType type;
    private final ObjectProperty<Position> position = new SimpleObjectProperty<>();

    /**
     * Constructs a {@code Piece} object.
     *
     * @param type     the {@code type} of the piece.
     * @param position the {@code position} where the piece is needed to be constructed.
     */
    public Piece(PieceType type, Position position) {
        this.type = type;
        this.position.set(position);
    }

    /**
     * Returns the {@code type} of the {@code piece} as a {@code PieceType}.
     *
     * @return the type of the piece.
     */
    public PieceType getType() {
        return type;
    }

    /**
     * Returns the {@code position} of the {@code piece} as a {@code Position} object.
     *
     * @return the position of the piece.
     */
    public Position getPosition() {
        return position.get();
    }

    /**
     * Moves the piece by {@code Direction}.
     *
     * @param direction the direction where the piece is needed to be moved.
     */
    public void moveTo(Direction direction) {
        Position newPosition = position.get().moveTo(direction);
        position.set(newPosition);
    }

    /**
     * Returns the {@code Position} of the {@code piece} as a {@code Position ObjectProperty}.
     *
     * @return the position of the piece.
     */
    public ObjectProperty<Position> positionProperty() {
        return position;
    }

    /**
     * Returns the string representation of the object in the following format:
     * {@code "type"} + {@code "position"}
     * eg. BLACK(0,0).
     *
     * @return the string representation of the position.
     * @return the string representation of the position.
     */
    public String toString() {
        return type.toString() + position.get().toString();
    }

}
