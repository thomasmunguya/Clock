package com.theschool.clock.model;

import java.util.Objects;

/**
 * Represents a time.
 *
 * @see Timeable
 * @see Stopwatch
 * @see Timer
 *
 * @author Thomas Munguya.
 */
public class Time {

    /**
     * The hour of the time.
     */
    private int hour;

    /**
     * The minute of the time.
     */
    private int minute;

    /**
     * The second of the time.
     */
    private int second;

    /**
     * Constructs a new {@code Time}.
     */
    public Time() {
    }

    /**
     * Constructs a new {@code Time} with the provided arguments.
     * @param hour the hour.
     * @param minute the minute.
     * @param second the second.
     * @throws IllegalArgumentException if any of the provided arguments is less than zero.
     */
    public Time(int hour, int minute, int second) {
        validateEntry(hour, "hour");
        validateEntry(minute, "minute");
        validateEntry(second, "second");
        adjustTime(hour, minute, second);
    }

    /**
     * Returns the hour of the time.
     * @return the hour.
     */
    public int getHour() {
        return hour;
    }

    /**
     * Sets the hour of the time.
     * @param hour the hour.
     * @throws IllegalArgumentException if {@code hour} is less than zero.
     */
    public void setHour(int hour) {
        validateEntry(hour, "hour");
        this.hour = hour;
    }

    /**
     * Returns the minute of the time.
     * @return the minute.
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Sets the minute of the time.
     * @param minute the minute.
     * @throws IllegalArgumentException if {@code minute} is less than zero.
     */
    public void setMinute(int minute) {
        validateEntry(minute, "minute");
        this.minute = minute;
    }

    /**
     * Returns the second of the time.
     * @return the second.
     */
    public int getSecond() {
        return second;
    }

    /**
     * Sets the second of the time.
     * @param second the second.
     * @throws IllegalArgumentException if {@code second} is less than zero.
     */
    public void setSecond(int second) {
        validateEntry(second, "second");
        this.second = second;
    }

    /**
     * Adds {@code t} to this time.
     * @param t the time to add to this time.
     * @return the result of adding {@code t} to this time.
     * @throws IllegalArgumentException if {@code t} is {@code null}.
     */
    public Time add(Time t) {
        if(t == null) {
            throw new IllegalArgumentException("Time argument cannot be null.");
        }

        // add the hours
        int resultHour = getHour() + t.getHour();

        // add the minutes
        int resultMinute = getMinute() + t.getMinute();
        // if the minutes are 60 or more,
        if(resultMinute >= 60) {
            // subtract 60 from the minutes
            resultMinute -= 60;
            // add 1 to the hours
            resultHour++;
        }

        // add the seconds
        int resultSecond = getSecond() + t.getSecond();
        // if the seconds are 60 or more,
        if(resultSecond >= 60) {
            // subtract 60 from the seconds
            resultSecond -= 60;
            // add 1 to the minutes
            resultMinute++;
        }

        return new Time(resultHour, resultMinute, resultSecond);
    }

    /**
     * Subtracts {@code t} from this time.
     * @param t the time to add to this time.
     * @return the result of adding {@code t} to this time.
     * @throws IllegalArgumentException if {@code t} is {@code null}.
     */
    public Time subtract(Time t) {
        if(t == null) {
            throw new IllegalArgumentException("Time argument cannot be null.");
        }

        // subtract the hours
        int resultHour = getHour() - t.getHour();

        // subtract the minutes
        int resultMinute = getMinute() - t.getMinute();
        // if the minutes are less than 0
        if(resultMinute < 0) {
            // add 60 to the minutes
            resultMinute += 60;
            // subtract 1 from the hours
            resultHour--;
        }

        // subtract the seconds
        int resultSecond = getSecond() - t.getSecond();
        // if the seconds are less than 0
        if(resultSecond < 0) {
            // check if the minutes are greater than 0. If so...
            if(resultMinute > 0) {
                // subtract 1 from the minutes.
                resultMinute--;
                // and add 60 to the seconds.
                resultSecond += 60;
            }
            // otherwise
            else if(resultHour > 0) {
                // subtract 1 from the hours.
                resultHour--;
                // add 59 to the minutes.
                resultMinute += 59;
                // and add 60 to the seconds.
                resultSecond += 60;
            }
        }

        return new Time(resultHour, resultMinute, resultSecond);
    }

    /**
     * Returns a string representation of this time.
     * @return the string representation of this time.
     */
    @Override
    public String toString() {
        return String.format("%s:%s:%s",
                getHour() > 9 ? getHour() : "0" + getHour(),
                getMinute() > 9 ? getMinute() : "0" + getMinute(),
                getSecond() > 9 ? getSecond() : "0" + getSecond());
    }

    /**
     * Helper method for adjusting the time.
     * @param hours the hours of the time to adjust.
     * @param minutes the minutes of the time to adjust.
     * @param seconds the seconds of the time to adjust.
     */
    private void adjustTime(int hours, int minutes, int seconds) {

        if(minutes >= 60) {
            hours = minutes / 60;
            minutes %= 60;
        }

        if(seconds >= 60) {
            minutes = seconds / 60;
            seconds %= 60;
        }

        this.hour = hours;
        this.minute = minutes;
        this.second = seconds;
    }

    /**
     * Returns {@code true} if this Time is equal to {@code otherTime}
     */
    @Override
    public boolean equals(Object otherTime) {
        if (this == otherTime) return true;
        if (otherTime == null || getClass() != otherTime.getClass()) return false;
        Time time = (Time) otherTime;
        return hour == time.hour && minute == time.minute && second == time.second;
    }

    /**
     * Returns a suitable hash code for this time.
     */
    @Override
    public int hashCode() {
        return Objects.hash(hour, minute, second);
    }

    /**
     * Validates a value entered for an hour, minute or second.
     * @param value the value.
     * @param entry the name of the entry, e.g hour.
     * @throws IllegalArgumentException if {@code value} is less than zero.
     */
    private void validateEntry(int value, String entry) {
        if(value < 0) {
            throw new IllegalArgumentException("Invalid value provided for " + entry + ".");
        }
    }
}
