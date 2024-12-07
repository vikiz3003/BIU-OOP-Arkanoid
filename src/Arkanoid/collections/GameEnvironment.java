//322907619 Victoria jeleznyak
package Arkanoid.collections;

import Arkanoid.logic.Collidable;
import Arkanoid.logic.CollisionInfo;
import Arkanoid.geometry.Line;
import Arkanoid.geometry.Point;

import java.util.List;

/**
 * The Arkanoid.collections.GameEnvironment class manages the collection of collidable objects in the Arkanoid.game.
 * It provides methods to add collidables and find the closest collision with a given trajectory.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructs a Arkanoid.collections.GameEnvironment with a list of initial collidables.
     *
     * @param collidables the initial list of collidables
     */
    public GameEnvironment(List<Collidable> collidables) {
        this.collidables = collidables;
    }


    /**
     * Adds a collidable object to the Arkanoid.game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }


    /**
     * Finds the closest collision between a trajectory and any collidable object.
     *
     * @param trajectory the trajectory to check for collisions
     * @return information about the closest collision point and collidable object, or null if no collision occurs
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int flag = 0;
        Point closestPoint = new Point(Double.MAX_VALUE, Double.MAX_VALUE);
        double distance = closestPoint.distance(trajectory.start());
        Collidable collisionObject = null;
        for (Collidable c : collidables) {
            Point closestPointInCollidable = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (closestPointInCollidable != null) { //checks if there is at least one collision
                flag = 1;
                double d = closestPointInCollidable.distance(trajectory.start());
                if (d < distance) {
                    closestPoint = closestPointInCollidable;
                    distance = d;
                    collisionObject = c;
                }
            }
        }
        // If no collisions were found, return null. otherwise, return collision information
        if (flag == 0) {
            return null;
        }
        return new CollisionInfo(closestPoint, collisionObject);
    }

    /**
     * Retrieves the list of collidables currently in the Arkanoid.game environment.
     *
     * @return the list of collidables
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }
}

