package Arkanoid.utils;//322907619 Victoria jeleznyak
/**
 * The Arkanoid.utils.Counter class is a simple counter that can increase or decrease its value.
 */
public class Counter {
    private int value = 0;


    /**
     * Increases the counter by a specified number.
     *
     * @param number the amount to increase the counter by
     */
    public void increase(int number) {
        value += number;
    }


    /**
     * Decreases the counter by a specified number.
     *
     * @param number the amount to decrease the counter by
     */
    public void decrease(int number) {
        value -= number;
    }


    /**
     * Gets the current value of the counter.
     *
     * @return the current value of the counter
     */
    public int getValue() {
        return value;
    }
}
