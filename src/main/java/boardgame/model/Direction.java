package boardgame.model;

/**
 * An Interface for the different {@code Directions}.
 */
public interface Direction {

    /**
     * Returns the change of the row after moving {@code Direction}.
     *
     * @return the change of the row after moving {@code Direction}.
     */
    int getRowChange();

    /**
     * Returns the change of the column after moving {@code Direction}.
     *
     * @return the change of the column after moving {@code Direction}.
     */
    int getColChange();

}
