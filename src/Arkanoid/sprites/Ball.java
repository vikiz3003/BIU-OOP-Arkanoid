//322907619 Victoria jeleznyak
package Arkanoid.sprites;

import Arkanoid.logic.CollisionInfo;
import Arkanoid.game.Game;
import Arkanoid.collections.GameEnvironment;
import Arkanoid.geometry.Line;
import Arkanoid.geometry.Point;
import Arkanoid.geometry.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;


/**
 * Represents a ball in a 2D space.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v = new Velocity(0, 0);
    private GameEnvironment gameEnvironment;


    /**
     * Constructor for creating a new Arkanoid.sprites.Ball.
     *
     * @param center          the center point of the ball
     * @param r               the radius of the ball
     * @param color           the color of the ball
     * @param gameEnvironment the Arkanoid.game environment where the ball exists
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Get the x-coordinate of the ball's center.
     *
     * @return the x-coordinate of the center
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Get the y-coordinate of the ball's center.
     *
     * @return the y-coordinate of the center
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Get the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return r;
    }


    /**
     * Get the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return color;
    }


    /**
     * Draw the ball on a given DrawSurface.
     *
     * @param surface the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), r);
    }

    /**
     * Update the ball's position based on its velocity.
     * Handle collisions with other objects in the Arkanoid.game environment.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }


    /**
     * Set the velocity of the ball.
     *
     * @param v the velocity to set
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Set the velocity of the ball given its components.
     *
     * @param dx the change in x
     * @param dy the change in y
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Get the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Get the center point of the ball.
     *
     * @return the center point of the ball
     */
    public Point getCenter() {
        return center;
    }


    /**
     * Move the ball one step based on its current velocity.
     * Handle collisions with objects in the Arkanoid.game environment.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
        CollisionInfo collisionDetails = this.gameEnvironment.getClosestCollision(trajectory);

        if (collisionDetails == null) {
            // No collision, move the ball to the end of the trajectory
            this.center = trajectory.end();
        } else {
            // Collision detected, move the ball to just before the collision point
            double collisionDistance = this.center.distance(collisionDetails.collisionPoint());
            double currentSpeed = Math.sqrt(Math.pow(this.v.getDx(), 2) + Math.pow(this.v.getDy(), 2));
            double preCollisionX = this.center.getX() + this.v.getDx() * (collisionDistance / currentSpeed) * 0.95;
            double preCollisionY = this.center.getY() + this.v.getDy() * (collisionDistance / currentSpeed) * 0.95;
            this.center = new Point(preCollisionX, preCollisionY);

            // Update the velocity after the collision
            this.setVelocity(collisionDetails.collisionObject().hit(this, collisionDetails.collisionPoint(), this.v));

            // Special handling for paddle collisions to prevent the ball from sticking
            if ("Arkanoid.sprites.Paddle".equals(collisionDetails.collisionObject().collideType())
                    && center.inArea(collisionDetails.collisionObject().getCollisionRectangle())) {
                this.center = new Point(getCenter().getX(), getCenter().getY() - 10);
            }
        }
    }

    /**
     * Add this ball to the specified Arkanoid.game.
     *
     * @param g the Arkanoid.game.Game object to add the ball to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Removes this sprite from the specified Arkanoid.game.
     *
     * @param g the Arkanoid.game from which to remove this sprite
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }

    /**
     * Sets the color of this sprite.
     *
     * @param color the new color for this sprite
     */
    public void setColor(Color color) {
        this.color = color;
    }
}