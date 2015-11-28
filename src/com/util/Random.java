package com.util;

/**
 * Random
 * <p>
 * A singleton of the java.util.Random class to avoid over-creation of the Random class, as instantiation is quite costly.
 * A few convenience methods are also included for getting random numbers within an interval.
 *
 * @author Ian
 * @version 1.0
 */
public class Random extends java.util.Random {
    private static Random ourInstance = new Random();

    private Random() {
    }

    /**
     * The singleton instance of this class.
     *
     * @return the single class instance
     */
    public static Random getInstance() {
        return ourInstance;
    }

    /**
     * Retrieves a random value between the specified interval.
     *
     * @param min the lowest value (inclusive)
     * @param max the highest value (exclusive)
     * @return a random value within the interval
     */
    public static int nextInt(int min, int max) {
        return min + (max == min ? 0 : getInstance().nextInt(max < min ? min - max : max - min));
    }

    /**
     * Retrieves a random value between the specified interval.
     *
     * @param min the lowest value (inclusive)
     * @param max the highest value (exclusive)
     * @return a random value within the interval
     */
    public static double nextDouble(final double min, final double max) {
        return min + getInstance().nextDouble() * (max - min);
    }

    /**
     * Retrieves a random value between the specified interval using a normal distribution.
     *
     * @param min the lowest value (inclusive)
     * @param max the highest value (exclusive)
     * @param sd  the standard deviation from the mean
     * @return a random value within the interval
     */
    public static int nextGaussian(final int min, final int max, final int sd) {
        return nextGaussian(min, max, min + (max - min) / 2, sd);
    }

    /**
     * Retrieves a random value between the specified interval using a normal distribution based around the specified mean.
     *
     * @param min  the lowest value (inclusive)
     * @param max  the highest value (exclusive)
     * @param mean the mean value
     * @param sd   the standard deviation from the mean
     * @return a random value within the interval
     */
    public static int nextGaussian(final int min, final int max, final int mean, final int sd) {
        if (min == max)
            return min;
        int rand;
        do
            rand = (int) (getInstance().nextGaussian() * sd + mean);
        while (rand < min || rand >= max);
        return rand;
    }
}
