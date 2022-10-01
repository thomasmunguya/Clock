package com.theschool.clock.gui;

import com.theschool.clock.model.Time;
import com.theschool.clock.model.Timer;
import com.theschool.clock.util.Constants;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

/**
 * Represents the graphical user interface for a timer.
 *
 * @see Timer
 */
public class TimerGUI extends Container {

    /**
     * The start button.
     */
    private final JButton startButton;

    /**
     * Defines the reset button.
     */
    private final JButton resetButton;

    /**
     * Defines the hours text field.
     */
    private final JTextField hoursTextField;

    /**
     * Defines the minutes text field.
     */
    private final JTextField minutesTextField;

    /**
     * Defines the seconds text field.
     */
    private final JTextField secondsTextField;

    /**
     * Defines underlying timer of the timer user interface.
     */
    private Timer timer;

    /**
     * Defines the timer text field.
     */
    private final JTextField timerTextField;

    /**
     * Defines the timer thread.
     */
    private static Thread timerThread;

    /**
     * Constructs a {@code TimerGUI}.
     */
    public TimerGUI() {
        timer = new Timer();
        this.startButton = new JButton(Constants.START);
        this.startButton.setEnabled(false);
        this.resetButton = new JButton(Constants.RESET);
        this.hoursTextField = new JTextField();
        this.minutesTextField = new JTextField();
        this.secondsTextField = new JTextField();
        this.timerTextField = new JTextField();
        
        registerButtonEventListeners();
        registerTimeTextFieldsEventListeners();
        setupGui();
    }

    /**
     * Registers event listeners for the buttons.
     */
    private void registerButtonEventListeners() {
        startButton.addActionListener((e) -> {

            String buttonText = startButton.getText();

            switch (buttonText) {
                case Constants.START -> startTimer();
                case Constants.PAUSE -> pauseTimer();
                case Constants.RESUME -> resumeTimer();
            }
        });

        resetButton.addActionListener((e) -> {
            timer.reset();
            timer.setRunning(false);
            startButton.setText(Constants.START);
        });
    }

    /**
     * Registers event listeners for the time text fields.
     */
    private void registerTimeTextFieldsEventListeners() {
        DocumentListener documentListener = new TextFieldDocumentListener();
        hoursTextField.getDocument().addDocumentListener(documentListener);
        minutesTextField.getDocument().addDocumentListener(documentListener);
        secondsTextField.getDocument().addDocumentListener(documentListener);
    }

    /**
     * Sets up the GUI.
     */
    private void setupGui() {
        timerTextField.setPreferredSize(new Dimension(310, 40));
        timerTextField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        timerTextField.setHorizontalAlignment(JTextField.CENTER);
        timerTextField.setEditable(false);

        hoursTextField.setText("0");
        hoursTextField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        hoursTextField.setPreferredSize(new Dimension(50, 40));

        minutesTextField.setText("0");
        minutesTextField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        minutesTextField.setPreferredSize(new Dimension(50, 40));

        secondsTextField.setText("0");
        secondsTextField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        secondsTextField.setPreferredSize(new Dimension(50, 40));

        JLabel hoursLabel = new JLabel("Hours");
        JLabel minutesLabel = new JLabel("Minutes");
        JLabel secondsLabel = new JLabel("Seconds");

        SpringLayout springLayout = new SpringLayout();
        springLayout.putConstraint(SpringLayout.EAST, hoursLabel, 50, SpringLayout.WEST, hoursTextField);
        springLayout.putConstraint(SpringLayout.WEST, hoursLabel, 5, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, hoursLabel, 20, SpringLayout.NORTH, this);

        springLayout.putConstraint(SpringLayout.WEST, hoursTextField, 50, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, hoursTextField, 10, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, hoursLabel, 50, SpringLayout.WEST, minutesLabel);
        
        this.setLayout(springLayout);

        JPanel mainJPanel = new JPanel();
        mainJPanel.setLayout(new FlowLayout());
        mainJPanel.add(hoursLabel);
        mainJPanel.add(hoursTextField);
        mainJPanel.add(minutesLabel);
        mainJPanel.add(minutesTextField);
        mainJPanel.add(secondsLabel);
        mainJPanel.add(secondsTextField);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        buttonsPanel.add(startButton);
        buttonsPanel.add(resetButton);

        springLayout.putConstraint(SpringLayout.WEST, mainJPanel, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, mainJPanel, 10, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.NORTH, timerTextField, 10, SpringLayout.SOUTH, mainJPanel);
        springLayout.putConstraint(SpringLayout.WEST, timerTextField, 10, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, buttonsPanel, 10, SpringLayout.SOUTH, timerTextField);
        springLayout.putConstraint(SpringLayout.WEST, buttonsPanel, 90, SpringLayout.WEST, this);

        this.add(mainJPanel);
        this.add(timerTextField);
        this.add(buttonsPanel);
    }

    /**
     * Starts the timer.
     */
    private void startTimer() {
        int hours = Integer.parseInt(hoursTextField.getText());
        int minutes = Integer.parseInt(minutesTextField.getText());
        int seconds = Integer.parseInt(secondsTextField.getText());

        if(hours != 0 || minutes != 0 || seconds != 0) {
            startButton.setText(Constants.PAUSE);
        }

        timer = new Timer(new Time(hours, minutes, seconds), true, timerTextField);

        // if the thread has not been initialized or its in a terminated state...
        if(timerThread == null || timerThread.getState().equals(Thread.State.TERMINATED)) {
            // then create a new thread.
            timerThread = new Thread(timer);
        }

        timerThread.start();
    }

    /**
     * Pauses the timer.
     */
    private void pauseTimer() {
        startButton.setText(Constants.RESUME);
        timer.setRunning(false);
    }

    /**
     * Resumes the timer.
     */
    private void resumeTimer() {
        startButton.setText(Constants.PAUSE);
        timer.setRunning(true);
        if(timerThread.getState().equals(Thread.State.TERMINATED)) {
            timerThread = new Thread(timer);
            timerThread.start();
        }
    }

    /**
     * Defines the document listener for the timer text fields.
     */
    private class TextFieldDocumentListener implements DocumentListener {
        public void changedUpdate(DocumentEvent e) {
            validateText();
        }
        public void removeUpdate(DocumentEvent e) {
            validateText();
        }
        public void insertUpdate(DocumentEvent e) {
            validateText();
        }

        /**
         * Validates the entries of hours, minutes and seconds text fields.
         */
        private void validateText() {
            startButton.setEnabled(
                    isValidEntry(hoursTextField.getText()) &&
                            isValidEntry(minutesTextField.getText()) &&
                            isValidEntry(secondsTextField.getText()));
        }

        /**
         * Whether the entry timer text field is valid.
         * @param value the value of the entry.
         * @return {@code true} if the entry is valid, and {@code false} otherwise.
         */
        private boolean isValidEntry(String value) {
            return value.matches("\\b[0-9]+\\b"); // regular expression for positive integers only.
        }
    }
}
