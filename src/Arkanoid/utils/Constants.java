//322907619 Victoria jeleznyak
package Arkanoid.utils;

/**
 * This class holds constants used in the bouncing balls animation.
 */
public class Constants {
    public static final double E = 0.00000000000001;
    public static final int MIN_BALL_SIZE = 3;
    public static final int MAX_BALL_SIZE = 80;
    public static final int MAX_BALL_SIZE_SECOND = 50;


    /**
     * Checks if two double values are approximately equal.
     *
     * @param a the first double value
     * @param b the second double value
     * @return true if the two double values are approximately equal, false otherwise
     */
    public static boolean equalsDouble(double a, double b) {
        return Math.abs(a - b) < E;
    }


    /**
     * Checks if a double value is less than or approximately equal to another double value.
     *
     * @param a the first double value
     * @param b the second double value
     * @return true if a is less than or approximately equal to b, false otherwise
     */
    public static boolean lessOrEqual(double a, double b) {
        return a < b || Constants.equalsDouble(a, b);
    }


    /**
     * Checks if a double value is greater than or approximately equal to another double value.
     *
     * @param a the first double value
     * @param b the second double value
     * @return true if a is greater than or approximately equal to b, false otherwise
     */
    public static boolean biggerOrEqual(double a, double b) {
        return a > b || Constants.equalsDouble(a, b);
    }

}

