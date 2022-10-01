package com.theschool.clock.model;

import javax.swing.text.JTextComponent;

/**
 * Represents a stopwatch.
 * <p>
 * A stopwatch is characterized by its ability to count time up.
 *
 * @see Time
 * @see Timeable
 *
 * @author Thomas Munguya
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
     * @throws IllegalArgumentException if {@code component} is {@code null}.
     */
    public Stopwatch(boolean running, JTextComponent component) {
        super(running, component);
    }

    /**
     * Counts the time up.
     */
    private void countUp() {
        time = time.add(new Time(0, 0, 1));
        component.setText(time.toString());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resets the stopwatch.
     */
    @Override
    public void reset() {
        time = new Time();
        component.setText(time.toString());
        setRunning(false);
    }

    @Override
    public void run() {
        while(isRunning()) {
            countUp();
        }
    }
}
