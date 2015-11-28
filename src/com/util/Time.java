package com.util;

/**
 * Time
 * <p>
 * A utility class which handles thread sleeps and time related operations.
 *
 * @author Ian
 * @version 1.0
 */
public class Time {
    private Time() {

    }

    /**
     * Pauses the current thread for the specified time.
     *
     * @param time the duration
     * @return <code>true</code> if the thread paused for the full time without interruption, <code>false</code> otherwise
     */
    public static boolean sleep(long time) {
        try {
            Thread.sleep(time);
            return true;
        } catch (final InterruptedException ignored) {
            //Failed to sleep full time due to the exception
            return false;
        }
    }

    /**
     * Pauses the current thread for a random time within the specified interval
     *
     * @param min the minimum time to sleep (inclusive)
     * @param max the maximum time to sleep (exclusive)
     * @return <code>true</code> if the thread paused for the full time without interruption, <code>false</code> otherwise
     */
    public static boolean sleep(int min, int max) {
        return sleep(Random.nextInt(min, max));
    }
}