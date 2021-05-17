package boardgame.model;

/**
 * Possible moves by a Knight piece represented in {@link Enum}.
 */
public enum KnightDirection implements Direction {

    /**
     * Represents left up knight move.
     */
    LEFT_UP(-1, -2),

    /**
     * Represents up left knight move.
     */
    UP_LEFT(-2, -1),

    /**
     * Represents up right knight move.
     */
    UP_RIGHT(-2, 1),

    /**
     * Represents right up knight move.
     */
    RIGHT_UP(-1, 2),

    /**
     * Represents right down knight move.
     */
    RIGHT_DOWN(1, 2),

    /**
     * Represents down right knight move.
     */
    DOWN_RIGHT(2, 1),

    /**
     * Represents down left knight move.
     */
    DOWN_LEFT(2, -1),

    /**
     * Represents left down knight move.
     */
    LEFT_DOWN(1, -2);

    private final int rowChange;
    private final int colChange;

    KnightDirection(int rowChange, int colChange) {
        this.rowChange = rowChange;
        this.colChange = colChange;
    }

    /**
     * Returns the number of row changes.
     *
     * @return the change of the row
     */
    public int getRowChange() {
        return rowChange;
    }

    /**
     * Returns the number of column changes.
     *
     * @return the change of the column
     */
    public int getColChange() {
        return colChange;
    }

    /**
     * Returns the directions of {@code rowChange} and {@code colChange}
     * if they are valid instances of the {@code KnightDirection} {@link Enum}.
     *
     * @param rowChange represents the change of the row
     * @param colChange represents the change of the column
     * @return an instance of {@code KnightDirection}
     * @throws IllegalArgumentException if the instance is not valid
     */
    public static KnightDirection of(int rowChange, int colChange) {
        for (var direction : values()) {
            if (direction.rowChange == rowChange && direction.colChange == colChange) {
                return direction;
            }
        }
        throw new IllegalArgumentException();
    }

}
