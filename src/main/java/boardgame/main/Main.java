package boardgame.main;

import javafx.application.Application;

/**
 * This is the program's "fake" Main class. You can find more information about this solution <a href="https://github.com/javafxports/openjdk-jfx/issues/236">here</a>.
 * It' calls the {@code BoardGameApplication}'s class, which starts the application.
 */
public class Main {

    /**
     * Main method. It calls the {@code BoardGameApplication}'s class.
     *
     * @param args optional argument.
     */
    public static void main(String[] args) {
        Application.launch(BoardGameApplication.class, args);
    }

}
