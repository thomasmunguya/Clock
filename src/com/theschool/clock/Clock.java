package com.theschool.clock;


import com.theschool.clock.gui.StopwatchGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * The {@code Clock} class represents the entry point for the clock application.
 */
public class Clock {
    /**
     * Defines the main pane.
     */
    private JPanel mainJPanel;

    /**
     * Defines the stopwatch button.
     */
    private JButton stopwatchButton;

    /**
     * Defines the timer
     */
    private JButton timerButton;

    /**
     * Defines the exit button.
     */
    private JButton exitButton;

    /**
     * Defines the graphical user interface for the stopwatch.
     */
    private static StopwatchGUI stopwatchGUI;

    /**
     * Defines the JFrame for the stopwatch.
     */
    private static JFrame stopwatchJFrame;

    /**
     * Defines the dimensions of the screen.
     */
    private static final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Defines the main JFrame.
     */
    private static final JFrame mainJFrame = new JFrame();

    /**
     * Defines an instance of this class.
     */
    private static final Clock clock = new Clock();

    public static void main(String[] args) {

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        flowLayout.setHgap(20);
        JPanel startStopButtonsPanel = new JPanel(flowLayout);
        startStopButtonsPanel.add(clock.timerButton);
        startStopButtonsPanel.add(clock.stopwatchButton);

        clock.mainJPanel.add(startStopButtonsPanel);

        SpringLayout springLayout = new SpringLayout();
        springLayout.putConstraint(SpringLayout.WEST, startStopButtonsPanel, 5, SpringLayout.WEST, clock.mainJPanel);
        springLayout.putConstraint(SpringLayout.NORTH, startStopButtonsPanel, 10, SpringLayout.NORTH, clock.mainJPanel);
        springLayout.putConstraint(SpringLayout.NORTH, clock.exitButton, 10, SpringLayout.SOUTH, startStopButtonsPanel);

        springLayout.putConstraint(SpringLayout.WEST, clock.exitButton, 80, SpringLayout.WEST, clock.mainJPanel);

        clock.mainJPanel.setLayout(springLayout);

        int x = (int) ((dimension.getWidth() - mainJFrame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - mainJFrame.getHeight()) / 2);

        mainJFrame.setLocation(x, y);
        mainJFrame.setContentPane(clock.mainJPanel);
        mainJFrame.setTitle("Clock");
        mainJFrame.setSize(240, 135);
        mainJFrame.setResizable(false);
        mainJFrame.setVisible(true);
    }

    /**
     * A listener for the stopwatch window.
     */
    private static class StopwatchWindowListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            stopwatchGUI = null;
            stopwatchJFrame.dispose();
        }

        @Override
        public void windowClosed(WindowEvent e) {}

        @Override
        public void windowIconified(WindowEvent e) {}

        @Override
        public void windowDeiconified(WindowEvent e) {}

        @Override
        public void windowActivated(WindowEvent e) {}

        @Override
        public void windowDeactivated(WindowEvent e) {}
    }
}
