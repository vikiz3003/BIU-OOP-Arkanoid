//322907619 Victoria jeleznyak
package Arkanoid.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The Arkanoid.geometry.Rectangle class represents a rectangle defined by its upper-left corner, width, and height.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Creates a new rectangle with the specified location, width, and height.
     *
     * @param upperLeft the upper-left corner of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a (possibly empty) list of intersection points with the specified line.
     *
     * @param line the line to check for intersections with
     * @return a list of intersection points with the specified line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line l1 = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX(), upperLeft.getY() + height);
        Line l2 = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY());
        Line l3 = new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
        Line l4 = new Line(upperLeft.getX(), upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);
        Line[] lines = {l1, l2, l3, l4};
        List<Point> intersections = new ArrayList<>();
        for (Line l : lines) {
            Point intersect = line.intersectionWith(l);
            if (intersect != null) {
                intersections.add(intersect);
            }
        }
        return intersections;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY());
    }


}

