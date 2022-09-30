package com.theschool.clock.model;

import javax.swing.text.JTextComponent;

/**
 * The {@code Timeable} class is the superclass of all entities that are timeable.
 */
public abstract class Timeable {
    /**
     * Defines the underlying time of this Timeable.
     */
    protected Time time;

    /**
     * Defines the running state of this Timeable.
     */
    protected boolean running;

    /**
     * Defines the component to which this Timeable will write its time.
     */
    protected JTextComponent component;

    /**
     * Constructs a new {@code Timeable}.
     */
    public Timeable() {
        this.time = new Time();
    }

    /**
     * Constructs a {@code Timeable} with the provided time.
     * @param time the time associated with this Timeable.
     */
    public Timeable(Time time) {
        this.time = time;
    }

    /**
     * Constructs a {@code Timeable} with the provided arguments.
     * @param running the running state of this Timeable.
     * @param component the component to write the time to.
     */
    public Timeable(boolean running, JTextComponent component) {
        this.running = running;
        this.component = component;
        this.time = new Time();
    }


    /**
     * Constructs a {@code Timeable} with the provided arguments.
     * @param time the time associated with this Timeable.
     * @param running the running state of this Timeable.
     * @param component the component to write the time to.
     */
    public Timeable(Time time, boolean running, JTextComponent component) {
        this.time = time;
        this.running = running;
        this.component = component;
    }

    /**
     * Returns the time.
     * @return the time.
     */
    public Time getTime() {
        return time;
    }

    /**
     * Sets the time.
     * @param t the new time.
     */
    public void setTime(Time t) {
        time = t;
    }

    /**
     * Returns the running state of the {@code Timeable}.
     * @return {@code true} if the Timeable is running, and {@code false} otherwise.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets running state of this {@code Timeable}.
     * @param running the new running state.
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Resets the time for this Timeable.
     */
    public abstract void reset();
}
