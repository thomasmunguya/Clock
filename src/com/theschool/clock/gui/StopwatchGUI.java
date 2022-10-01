package com.theschool.clock.gui;



import com.theschool.clock.model.Stopwatch;
import com.theschool.clock.util.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the graphical user interface for the stopwatch.
 *
 * @see Stopwatch
 */
public class StopwatchGUI extends Container {
    /**
     * Defines the start button.
     */
    private final JButton startButton;

    /**
     * Defines the reset button.
     */
    private final JButton resetButton;

    /**
     * Defines the lap button.
     */
    private final JButton lapButton;

    /**
     * Defines the stopwatch text field.
     */
    private final JTextField stopwatchTextField;

    /**
     * Defines the underlying stopwatch of the user interface.
     */
    private Stopwatch stopwatch;

    /**
     * Defines the stopwatch thread.
     */
    private Thread stopwatchThread;

    /**
     * Defines the lap text area.
     */
    private final JTextArea lapTextArea;

    /**
     * Constructs a new {@code StopwatchGUI}.
     */
    public StopwatchGUI() {
        this.startButton = new JButton(Constants.START);
        this.resetButton = new JButton(Constants.RESET);
        this.lapButton = new JButton(Constants.LAP);
        this.stopwatchTextField = new JTextField();
        this.stopwatch = new Stopwatch();
        this.lapTextArea = new JTextArea();

        setUpGui();
        registerButtonEventListeners();
    }

    /**
     * Registers button event listeners.
     */
    private void registerButtonEventListeners() {
        startButton.addActionListener((e) -> {
            String buttonText = startButton.getText();

            switch(buttonText) {
                case Constants.START -> startStopwatch();
                case Constants.PAUSE -> pauseStopwatch();
                case Constants.RESUME -> resumeStopwatch();
                case Constants.RESET -> resetStopwatch();
                case Constants.LAP -> lapStopwatch();
            }
        });

        resetButton.addActionListener((e) -> resetStopwatch());

        lapButton.addActionListener((e) -> lapStopwatch());
    }

    private void startStopwatch() {
        if(stopwatchThread == null || stopwatchThread.getState().equals(Thread.State.TERMINATED)) {
            stopwatch = new Stopwatch(true, stopwatchTextField);
            stopwatchThread = new Thread(stopwatch);
            stopwatchThread.start();
            startButton.setText(Constants.PAUSE);
        }
    }

    /**
     * Resumes the stopwatch.
     */
    private void resumeStopwatch() {
        startButton.setText(Constants.PAUSE);
        if(stopwatchThread.getState().equals(Thread.State.TERMINATED)) {
            stopwatch.setRunning(true);
            stopwatchThread = new Thread(stopwatch);
            stopwatchThread.start();
        }
    }

    /**
     * Pauses the stopwatch.
     */
    private void pauseStopwatch() {
        startButton.setText(Constants.RESUME);
        stopwatch.setRunning(false);
    }

    /**
     * Resets the stopwatch.
     */
    private void resetStopwatch() {
        startButton.setText(Constants.START);
        stopwatch.reset();
        lapTextArea.setText("");
    }

    /**
     * Laps the stopwatch.
     */
    private void lapStopwatch() {
        lapTextArea.setText(lapTextArea.getText() + stopwatch.getTime() + "\n");
    }

    /**
     * Sets up the GUI.
     */
    private void setUpGui() {
        stopwatchTextField.setText("00:00:00");
        stopwatchTextField.setPreferredSize(new Dimension(300, 40));
        stopwatchTextField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        stopwatchTextField.setHorizontalAlignment(JTextField.CENTER);
        stopwatchTextField.setEditable(false);

        lapTextArea.setPreferredSize(new Dimension(300, 300));
        lapTextArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        lapTextArea.setEditable(false);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(startButton);
        buttonsPanel.add(resetButton);
        buttonsPanel.add(lapButton);

        SpringLayout springLayout = new SpringLayout();
        springLayout.putConstraint(SpringLayout.NORTH, stopwatchTextField, 10, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, stopwatchTextField, 10, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.WEST, buttonsPanel, 60, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, buttonsPanel, 10, SpringLayout.SOUTH, stopwatchTextField);

        springLayout.putConstraint(SpringLayout.NORTH, lapTextArea, 10, SpringLayout.SOUTH, buttonsPanel);
        springLayout.putConstraint(SpringLayout.WEST, lapTextArea, 10, SpringLayout.WEST, this);

        this.setLayout(springLayout);
        this.add(stopwatchTextField);
        this.add(buttonsPanel);
        this.add(lapTextArea);
    }
}
