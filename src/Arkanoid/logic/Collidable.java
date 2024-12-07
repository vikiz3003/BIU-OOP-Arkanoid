package Arkanoid.logic;

import Arkanoid.geometry.Point;
import Arkanoid.geometry.Rectangle;
import Arkanoid.geometry.Velocity;
import Arkanoid.sprites.Ball;

/**
 * Arkanoid.logic.Collidable represents an object that can participate in collisions.
 * It provides methods to retrieve its collision shape, handle collisions,
 * and indicate its collision type.
 */
public interface Collidable {

    /**
     * Returns the collision shape of the object.
     *
     * @return the Arkanoid.geometry.Rectangle representing the collision shape of the object
     */
    Rectangle getCollisionRectangle();


    /**
     * Notifies the object that a collision occurred at the specified collision point
     * with a given velocity.
     *
     * @param hitter           the ball that hit the object
     * @param collisionPoint  the point at which the collision occurred
     * @param currentVelocity the current velocity of the colliding object
     * @return the new velocity expected after the hit
     * (based on the force the object inflicted on this Arkanoid.logic.Collidable)
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Returns the type of collision this object represents.
     *
     * @return a string representing the type of collision (e.g., "Arkanoid.sprites.Paddle", "Arkanoid.sprites.Block")
     */
    String collideType();
}
