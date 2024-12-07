//322907619 Victoria jeleznyak
package Arkanoid.geometry;

import Arkanoid.utils.Constants;

import java.util.List;

/**
 * Represents a point in a 2D coordinate system.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructs a Arkanoid.geometry.Point with the specified coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the Euclidean distance between this point and another point.
     *
     * @param other the other point to which the distance is calculated
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        if (other == null) {
            return -1;
        }
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other the other point to compare with
     * @return true if the coordinates of both points are the same, false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return Math.abs(this.x - other.x) < Constants.E && Math.abs(this.y - other.y) < Constants.E;
    }

    /**
     * Gets the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of this point.
     *
     * @param x the new x-coordinate of this point
     */
    public void setX(double x) {
        this.x = x;
    }


    /**
     * Sets the y-coordinate of this point.
     *
     * @param y the new y-coordinate of this point
     */
    public void setY(double y) {
        this.y = y;
    }


    /**
     * Finds the closest point to a target point from a list of points.
     *
     * @param points      the list of points from which to find the closest point
     * @param targetPoint the target point to which the closest point is found
     * @return the closest point to the target point from the list, or null if the list is null or empty
     */
    public static Point findClosestPoint(List<Point> points, Point targetPoint) {
        if (points == null || points.isEmpty()) {
            return null; // Handle null or empty list
        }

        Point closestPoint = null;
        double minDistance = Double.MAX_VALUE;

        for (Point point : points) {
            double distance = point.distance(targetPoint);
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = point;
            }
        }
        return closestPoint;
    }

    /**
     * Checks if this point is within the area defined by a rectangle.
     *
     * @param rect the rectangle defining the area
     * @return true if this point is within the rectangle's area, false otherwise
     */
    public boolean inArea(Rectangle rect) {
        double rectX = rect.getUpperLeft().getX();
        double rectY = rect.getUpperLeft().getY();
        double rectWidth = rect.getWidth();
        double rectHeight = rect.getHeight();
        double ballX = this.getX();
        double ballY = this.getY();
        return (ballX >= rectX && ballX <= rectX + rectWidth
                && ballY >= rectY && ballY <= rectY + rectHeight);
    }
}