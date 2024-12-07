package Arkanoid.logic;

import Arkanoid.sprites.Ball;
import Arkanoid.sprites.Block;

/**
 * The Arkanoid.logic.HitListener interface should be implemented by any class that wants to be notified of hit events.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
