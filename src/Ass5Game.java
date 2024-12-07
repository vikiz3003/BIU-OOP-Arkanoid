//322907619 Victoria jeleznyak

import Arkanoid.game.Game;

/**
 * This class represents the main entry point for running the Arkanoid.game.
 * It initializes a new Arkanoid.game instance and starts its execution.
 */
public class Ass5Game {
    /**
     * The main method of the Arkanoid.game.
     * It creates a new Arkanoid.game.Game instance, initializes it, and starts the Arkanoid.game loop.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
