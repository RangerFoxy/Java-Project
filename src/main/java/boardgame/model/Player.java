package boardgame.model;

/**
 * {@code Player} types represented in Enum.
 */
public enum Player {

    /**
     * The type of the player with the dark piece.
     */
    DARK,

    /**
     * The type of the player with the light piece.
     */
    LIGHT;

    /**
     * Switches the {@code Player} types, it represents the turn based mechanism.
     *
     * @return the opposite {@code Player} type.
     */
    public Player alter() {
        return switch(this){
            case DARK -> LIGHT;
            case LIGHT -> DARK;
        };
    }
}
