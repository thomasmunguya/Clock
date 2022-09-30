package com.theschool.clock.model;

import javax.swing.text.JTextComponent;

/**
 * Represents a stopwatch.
 * <p>
 * A stopwatch is characterized by its ability to count time up.
 *
 * @see Time
 * @see Timeable
 */
public class Stopwatch extends Timeable implements Runnable {

    /**
     * Constructs a {@code Stopwatch}.
     **/
    public Stopwatch() {
        super();
    }

    /**
     * Constructs a {@code Stopwatch} with the provided arguments.
     * @param running the running state of this stopwatch.
     * @param component the component to write the time to.                
     */
    public Stopwatch(boolean running, JTextComponent component) {
        super(running, component);
    }

    /**
     * Counts the time up.
     */
    public void countUp() {
        throw new UnsupportedOperationException();
    }

    /**
     * Resets the stopwatch.
     */
    @Override
    public void reset() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException();
    }
}
