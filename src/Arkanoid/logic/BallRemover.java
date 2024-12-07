package Arkanoid.logic;

import Arkanoid.utils.Counter;
import Arkanoid.game.Game;
import Arkanoid.sprites.Ball;
import Arkanoid.sprites.Block;

/**
 * Arkanoid.logic.BallRemover is responsible for removing balls from the Arkanoid.game
 * when they hit a specific block, and keeping track of the number of balls remaining.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;


    /**
     * Constructor for creating a Arkanoid.logic.BallRemover.
     *
     * @param game            the Arkanoid.game where the balls exist
     * @param remainingBalls  the counter for the remaining balls in the Arkanoid.game
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * It removes the ball from the Arkanoid.game and decreases the count of remaining balls.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that is hitting the block
     */

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
