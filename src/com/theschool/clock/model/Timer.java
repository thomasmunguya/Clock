package com.theschool.clock.model;

import javax.swing.text.JTextComponent;

/**
 * Represents a timer.
 * <p>
 * A timer is characterized by its ability to count down time.
 *
 *  @see Time
 *  @see Timeable
 */
public class Timer extends Timeable implements Runnable {
    /**
     * Represents the default time. The default time has a value of 00:00:00.
     */
    private static final Time DEFAULT_TIME = new Time();

    /**
     * Represents the time that this timer is initialized with.
     */
    private Time originalTime;

    /**
     * Constructs a {@code Timer}.
     */
    public Timer() {
        super();
    }

    /**
     * Constructs a {@code Timer} with the provided time.
     * @param time the time associated with this Timer.
     */
    public Timer(Time time) {
        super(time);
        this.originalTime = time;
    }

    /**
     * Constructs a {@code Timer} with the provided arguments.
     * @param time the time associated with this time.
     * @param running the running state of the timer.
     * @param component the component to write the time to.               
     */
    public Timer(Time time, boolean running, JTextComponent component) {
        super(time, running, component);
        this.originalTime = time;
    }

    /**
     * Counts down the time.
     */
    public void countDown() {
        throw new UnsupportedOperationException();
    }

    /**
     * Resets the timer.
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
