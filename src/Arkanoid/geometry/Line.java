
package Arkanoid.geometry;

import Arkanoid.utils.Constants;

import java.util.List;

/**
 * Represents a line segment defined by two endpoints.
 */
public class Line {
    private Point start;
    private Point end;


    /**
     * Constructs a line segment with the specified start and end points.
     *
     * @param start the starting point of the line segment
     * @param end   the ending point of the line segment
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a line segment with the specified coordinates.
     *
     * @param x1 the x-coordinate of the starting point
     * @param y1 the y-coordinate of the starting point
     * @param x2 the x-coordinate of the ending point
     * @param y2 the y-coordinate of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Calculates the length of the line segment.
     *
     * @return the length of the line segment
     */
    public double length() {
        return start.distance(end);
    }


    /**
     * Calculates the midpoint of the line segment.
     *
     * @return the midpoint of the line segment
     */
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }


    /**
     * Returns the starting point of the line segment.
     *
     * @return the starting point of the line segment
     */
    public Point start() {
        return start;
    }


    /**
     * Returns the ending point of the line segment.
     *
     * @return the ending point of the line segment
     */
    public Point end() {
        return end;
    }


    /**
     * Determines the orientation of the triplet (p1, p2, p3).
     *
     * @param p1 the first point
     * @param p2 the second point
     * @param p3 the third point
     * @return 0 if collinear, 1 if clockwise, 2 if counterclockwise
     */
    public int checkOrientation(Point p1, Point p2, Point p3) {
        double o = (p2.getY() - p1.getY()) * (p3.getX() - p2.getX())
                - (p2.getX() - p1.getX()) * (p3.getY() - p2.getY());
        if (o == 0) {
            return 0;
        }
        if (o > 0) {
            return 1;
        }
        return 2;
    }

    /**
     * Checks if the given point lies on the line segment.
     *
     * @param line the line segment
     * @param p    the point to check
     * @return true if the point lies on the line segment, false otherwise
     */
    public boolean isPointOnLine(Line line, Point p) {
        double maxX = Math.max(line.start.getX(), line.end.getX());
        double minX = Math.min(line.start.getX(), line.end.getX());
        double maxY = Math.max(line.start.getY(), line.end.getY());
        double minY = Math.min(line.start.getY(), line.end.getY());
        return (p.getX() < Math.max(line.start.getX(), line.end.getX()) || Constants.equalsDouble(p.getX(), maxX))
                && (p.getX() > Math.min(line.start.getX(), line.end.getX()) || Constants.equalsDouble(p.getX(), minX))
                && (p.getY() < Math.max(line.start.getY(), line.end.getY()) || Constants.equalsDouble(p.getY(), maxY))
                && (p.getY() > Math.min(line.start.getY(), line.end.getY()) || Constants.equalsDouble(p.getY(), minY));
    }


    /**
     * Checks if this line segment intersects with another line segment.
     *
     * @param other the other line segment
     * @return true if the line segments intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (other == null) {
            return false;
        }
        Point p1 = start;
        Point p2 = end;
        Point p3 = other.start;
        Point p4 = other.end;
        int o1 = checkOrientation(p1, p2, p3);
        int o2 = checkOrientation(p1, p2, p4);
        int o3 = checkOrientation(p3, p4, p1);
        int o4 = checkOrientation(p3, p4, p2);
        if (o1 != o2 && o3 != o4) {
            return true;
        }
        if (o1 == 0 && isPointOnLine(this, p3)) {
            return true;
        }
        if (o2 == 0 && isPointOnLine(this, p4)) {
            return true;
        }
        if (o3 == 0 && isPointOnLine(other, p1)) {
            return true;
        }
        return o4 == 0 && isPointOnLine(other, p2);
    }


    /**
     * Checks if this line segment intersects with two other line segments.
     *
     * @param other1 the first other line segment
     * @param other2 the second other line segment
     * @return true if this line intersects with both other line segments, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return isIntersecting(other1) && isIntersecting(other2);
    }


    /**
     * Finds the intersection point of this line segment with another line segment.
     *
     * @param other the other line segment
     * @return the intersection point, or null if the line segments do not intersect
     */
    public Point intersectionWith(Line other) {
        if (other == null) {
            return null;
        }

        double x1 = this.start.getX(), y1 = this.start.getY();
        double x2 = this.end.getX(), y2 = this.end.getY();
        double x3 = other.start.getX(), y3 = other.start.getY();
        double x4 = other.end.getX(), y4 = other.end.getY();

        // Special case where both lines are points
        if (this.start.equals(this.end) && other.start.equals(other.end)) {
            return this.start.equals(other.start) ? this.start : null;
        }

        double denominator = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        if (Constants.equalsDouble(denominator, 0)) {
            // Lines are parallel or coincident
            if (isOverlapping(other)) {
                if (this.end.equals(other.start) && !isPointOnLine(new Line(other.start, other.end), this.start)) {
                    return new Point(this.end.getX(), this.end.getY());
                }
                if (this.end.equals(other.end) && !isPointOnLine(new Line(other.start, other.end), this.start)) {
                    return new Point(this.end.getX(), this.end.getY());
                }
                if (this.start.equals(other.start) && !isPointOnLine(new Line(other.start, other.end), this.end)) {
                    return new Point(this.start.getX(), this.start.getY());
                }
                if (this.start.equals(other.end) && !isPointOnLine(new Line(other.start, other.end), this.end)) {
                    return new Point(this.start.getX(), this.start.getY());
                }
            }
            return null;
        }

        double numeratorA = (x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3);
        double numeratorB = (x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3);
        double uA = numeratorA / denominator;
        double uB = numeratorB / denominator;

        if (Constants.biggerOrEqual(uA, 0) && Constants.lessOrEqual(uA, 1)
                && Constants.biggerOrEqual(uB, 0) && Constants.lessOrEqual(uB, 1)) {
            double intersectionX = x1 + uA * (x2 - x1);
            double intersectionY = y1 + uA * (y2 - y1);
            return new Point(intersectionX, intersectionY);
        }

        return null; // No intersection found
    }


    /**
     * Calculates the incline between two points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the incline between the two points
     */
    public static double incline(Point p1, Point p2) {
        double deltaY = p2.getY() - p1.getY();
        double deltaX = p2.getX() - p1.getX();

        if (deltaX == 0) {
            return Double.POSITIVE_INFINITY; // Vertical line case
        }
        return deltaY / deltaX;
    }

    /**
     * Calculates the intercept of a line with the y-axis.
     *
     * @param point The point through which the line passes.
     * @param inc   The slope of the line.
     * @return The intercept of the line with the y-axis.
     */
    private double calculateB(Point point, double inc) {
        return point.getY() - (inc * point.getX());
    }

    /**
     * Checks if this line overlaps with another line.
     *
     * @param other The other line segment.
     * @return True if there is an overlap, otherwise false.
     */
    private boolean isOverlapping(Line other) {
        if (Constants.equalsDouble(this.start.getX(), this.end.getX())
                && Constants.equalsDouble(other.start.getX(), other.end.getX())
                && Constants.equalsDouble(this.start.getX(), other.end.getX())) {
            return (isPointOnLine(this, other.start));
        }
        if (Constants.equalsDouble(calculateB(this.start, incline(this.start, this.end)),
                calculateB(other.start, incline(other.start, other.end)))) {
            return (isPointOnLine(this, other.start));
        }
        return false;
    }


    /**
     * Checks if this line segment is equal to another line segment.
     *
     * @param other the other line segment
     * @return true if the line segments are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * Finds the closest intersection point to the start of this line with a given rectangle.
     *
     * @param rect the rectangle to check for intersections
     * @return the closest intersection point to the start of this line, or null if no intersections are found
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // Find all intersection points between this line and the rectangle
        List<Point> intersections = rect.intersectionPoints(this);
        // If no intersections are found, return null
        if (intersections == null || intersections.isEmpty()) {
            return null;
        }
        // Find the closest intersection point to the start point of this line
        return Point.findClosestPoint(intersections, this.start);
    }
}
