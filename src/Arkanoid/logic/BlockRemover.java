package Arkanoid.logic;

import Arkanoid.utils.Counter;
import Arkanoid.game.Game;
import Arkanoid.sprites.Ball;
import Arkanoid.sprites.Block;

/**
 * The Arkanoid.logic.BlockRemover class is responsible for removing blocks from the Arkanoid.game and keeping track
 * of the number of remaining blocks. It implements the Arkanoid.logic.HitListener interface to respond to hit events.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a Arkanoid.logic.BlockRemover.
     *
     * @param game            the Arkanoid.game from which blocks will be removed
     * @param remainingBlocks a counter for the number of remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }


    /**
     * This method is called whenever the beingHit object is hit.
     * Removes the block from the Arkanoid.game and updates the block counter.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setColor(beingHit.getColor());
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}
