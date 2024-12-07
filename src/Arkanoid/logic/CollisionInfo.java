package Arkanoid.logic;

import Arkanoid.geometry.Point;

/**
 * Arkanoid.logic.CollisionInfo represents information about a collision between two objects.
 * It includes the collision point and the collidable object involved.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;


    /**
     * Constructs a Arkanoid.logic.CollisionInfo instance with the collision point and collidable object.
     *
     * @param collisionPoint  the point at which the collision occurs
     * @param collisionObject the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }


    /**
     * Returns the collision point.
     *
     * @return a new Arkanoid.geometry.Point object representing the collision point
     */
    public Point collisionPoint() {
        return new Point(this.collisionPoint.getX(), this.collisionPoint.getY());
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object involved
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
