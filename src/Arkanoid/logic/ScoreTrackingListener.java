package Arkanoid.logic;

import Arkanoid.utils.Counter;
import Arkanoid.sprites.Ball;
import Arkanoid.sprites.Block;

/**
 * The Arkanoid.logic.ScoreTrackingListener class tracks hits on blocks and updates a score counter accordingly.
 * It implements the Arkanoid.logic.HitListener interface to respond to hit events.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a Arkanoid.logic.ScoreTrackingListener with a specified score counter.
     *
     * @param scoreCounter the counter to track the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * Increases the current score by a fixed amount and removes this listener from the hit object.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
        beingHit.removeHitListener(this);
    }
}
