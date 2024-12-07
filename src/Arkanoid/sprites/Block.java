//322907619 Victoria jeleznyak
package Arkanoid.sprites;

//import Arkanoid.*;
import Arkanoid.logic.Collidable;
import Arkanoid.game.Game;
import Arkanoid.logic.HitListener;
import Arkanoid.logic.HitNotifier;
import Arkanoid.geometry.Point;
import Arkanoid.geometry.Rectangle;
import Arkanoid.geometry.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The Arkanoid.sprites.Block class represents a rectangular block that can collide with other objects
 * and be drawn on a DrawSurface.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private List<HitListener> hitListeners = new LinkedList<>();

    /**
     * Constructs a Arkanoid.sprites.Block with a given top-left point, width, height, and color.
     *
     * @param point  the top-left point of the block
     * @param width  the width of the block
     * @param height the height of the block
     * @param color  the color of the block
     */
    public Block(Point point, double width, double height, Color color) {
        this.rect = new Rectangle(point, width, height);
        this.color = color;
    }

    /**
     * Returns the collision rectangle of the block.
     *
     * @return the collision rectangle of the block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(rect.getUpperLeft(), rect.getWidth(), rect.getHeight());
    }

    /**
     * Handles a collision between the block and another object.
     * Adjusts the velocity of the colliding object accordingly.
     *
     * @param hitter           the ball that is hitting the block
     * @param collisionPoint  the point of collision
     * @param currentVelocity the current velocity of the colliding object
     * @return the new velocity of the colliding object after the collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        if (collisionPoint.getX() == rect.getUpperLeft().getX()
                || collisionPoint.getX() == rect.getUpperLeft().getX() + rect.getWidth()) {
            dx = -dx;
        }
        if (collisionPoint.getY() == rect.getUpperLeft().getY()
                || collisionPoint.getY() == rect.getUpperLeft().getY() + rect.getHeight()) {
            dy = -dy;
        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        return new Velocity(dx, dy);
    }

    /**
     * Draws the block on a given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the block
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    /**
     * Performs actions when time passes in the Arkanoid.game (currently does nothing for Arkanoid.sprites.Block).
     */
    @Override
    public void timePassed() {
    }

    /**
     * Adds this block to the specified Arkanoid.game, enabling it to interact with the Arkanoid.game environment.
     *
     * @param g the Arkanoid.game.Game object to which this block will be added
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Returns the type of collision this block represents.
     *
     * @return a string representing the type of collision (always returns "Arkanoid.sprites.Block")
     */
    @Override
    public String collideType() {
        return "Arkanoid.sprites.Block";
    }

    /**
     * Checks if the ball's color matches the block's color.
     *
     * @param ball the ball to check
     * @return true if the colors match, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor() == this.color;
    }

    /**
     * Removes this block from the specified Arkanoid.game.
     *
     * @param game the Arkanoid.game from which to remove this block
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Adds a hit listener to the block.
     *
     * @param hl the Arkanoid.logic.HitListener to add
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from the block.
     *
     * @param hl the Arkanoid.logic.HitListener to remove
     */
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Notifies all registered hit listeners about a hit event.
     *
     * @param hitter the Arkanoid.sprites.Ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Gets the color of the block.
     *
     * @return the color of the block
     */
    public Color getColor() {
        return this.color;
    }
}

