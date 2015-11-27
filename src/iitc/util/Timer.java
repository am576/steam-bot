package iitc.util;

/**
 * Timer
 * <p>
 * An object which represents a basic timer/stopwatch.
 *
 * @author Ian
 * @version 1.0
 */
public class Timer {
    private long start;
    private long period;
    private long end;

    /**
     * Creates a timer with a specified period.
     *
     * @param period the length of each cycle
     */
    public Timer(long period) {
        this.period = period;
        init();
    }

    /**
     * Sets the time intervals for the timer, including the start time, end time, and period.
     */
    private void init() {
        end = (start = System.currentTimeMillis()) + period;
    }

    /**
     * Returns the total time elapsed since the beginning of the timer.
     *
     * @return the total time elapsed
     */
    public long getElapsed() {
        return System.currentTimeMillis() - start;
    }

    /**
     * Returns the total time remaining before the timer is finished counting.
     *
     * @return the total time remaining
     */
    public long getRemaining() {
        return isRunning() ? end - System.currentTimeMillis() : 0;
    }

    /**
     * Returns whether or not the timer is still valid and running.
     *
     * @return <code>true</code> if the current time is before the end time of the timer
     */
    public boolean isRunning() {
        return System.currentTimeMillis() < end;
    }

    /**
     * Re-establishes the time intervals for the timer.
     */
    public void reset() {
        init();
    }

    /**
     * Updates the end time for the timer.
     *
     * @param remaining the offset for the new end period of the timer
     */
    public void setEndIn(long remaining) {
        end = System.currentTimeMillis() + remaining;
    }
}