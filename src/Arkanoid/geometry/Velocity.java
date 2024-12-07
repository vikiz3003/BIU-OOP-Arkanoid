//322907619 Victoria jeleznyak
package Arkanoid.geometry;

/**
 * Represents a velocity in 2D space, defined by its change in x and y coordinates.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a new velocity with the given changes in x and y coordinates.
     *
     * @param dx the change in x-coordinate
     * @param dy the change in y-coordinate
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Applies the velocity to a given point, resulting in a new point after the velocity is applied.
     *
     * @param p the point to which the velocity is applied
     * @return the new point after applying the velocity
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);

    }

    /**
     * Get the change in x-coordinate.
     *
     * @return the change in x-coordinate
     */
    public double getDx() {
        return dx;
    }

    /**
     * Get the change in y-coordinate.
     *
     * @return the change in y-coordinate
     */
    public double getDy() {
        return dy;
    }

    /**
     * Set the change in x-coordinate.
     *
     * @param dx the change in x-coordinate to set
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Set the change in y-coordinate.
     *
     * @param dy the change in y-coordinate to set
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Create a new velocity from an angle and speed.
     *
     * @param angle the angle in degrees
     * @param speed the speed
     * @return the velocity corresponding to the given angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
}