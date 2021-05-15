package boardgame.model;

public enum Player {
    DARK,
    LIGHT;

    public Player alter() {
        return switch(this){
            case DARK -> LIGHT;
            case LIGHT -> DARK;
        };
    }
}
