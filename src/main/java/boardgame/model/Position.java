package boardgame.model;

/**
 * Record class for representing the positions of the board.
 *
 * @param row the row of the {@code position} on the board.
 * @param col the column of the {@code position} on the board.
 */
public record Position(int row, int col) {

    /**
     * Moves the {@code Position} to the direction of {@code Direction}.
     *
     * @param direction the direction where the position needed to be moved
     * @return the new {@code Position} object after it moved to the new tile
     */
    public Position moveTo(Direction direction) {
        return new Position(row + direction.getRowChange(), col + direction.getColChange());
    }

    /**
     * Returns the {@link String} representation of the {@code Position} object in the following format:
     * ({@code "row"},{@code "col"}).
     *
     * @return the String representation of the position
     */
    public String toString() {
        return String.format("(%d,%d)", row, col);
    }

}
