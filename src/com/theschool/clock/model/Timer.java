package com.theschool.clock.model;

import javax.swing.text.JTextComponent;

/**
 * Represents a timer.
 * <p>
 * A timer is characterized by its ability to count down time.
 *
 *  @see Time
 *  @see Timeable
 *
 * @author Thomas Munguya
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
     * @throws IllegalArgumentException if {@code time} is {@code null}.
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
     * @throws IllegalArgumentException if {@code component} or {@code time} is {@code null}.
     */
    public Timer(Time time, boolean running, JTextComponent component) {
        super(time, running, component);
        this.originalTime = time;
    }

    /**
     * Counts down the time.
     */
    private void countDown() {
        time = time.subtract(new Time(0, 0, 1));
        component.setText(time.toString());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resets the timer.
     */
    @Override
    public void reset() {
        time = originalTime;
        component.setText(time.toString());
    }

    @Override
    public void run() {
        while(isRunning()) {
            // if the timer reaches the default time (00:00:00), the stop it.
            if(time.equals(DEFAULT_TIME)) {
                setRunning(false);
                component.setText(DEFAULT_TIME.toString());
                return;
            }
            countDown();
        }
        if(!component.getText().equals(time.toString())) {
            component.setText(time.toString());
        }
    }
}
