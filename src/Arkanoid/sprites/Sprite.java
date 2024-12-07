package Arkanoid.sprites;//322907619 Victoria jeleznyak

import biuoop.DrawSurface;

/**
 * The Arkanoid.sprites.Sprite interface specifies that objects that implement it are able to be drawn on the screen and
 * notified that time has passed.
 */
public interface Sprite {

    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the sprite
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed, allowing it to update its state accordingly.
     */
    void timePassed();
}

