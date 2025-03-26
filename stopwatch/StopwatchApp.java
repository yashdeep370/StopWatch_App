//Program for Stopwatch
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopwatchApp {

    private JFrame frame;
    private JLabel timeLabel;
    private JButton startButton, stopButton, resetButton;
    private Timer timer;
    private long startTime;
    private boolean running = false;

    public StopwatchApp() {
        frame = new JFrame("Stopwatch");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        timeLabel = new JLabel("00:00:00:000", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(timeLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDisplay();
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startStopwatch();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopStopwatch();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetStopwatch();
            }
        });

        frame.setVisible(true);
    }

    private void startStopwatch() {
        if (!running) {
            startTime = System.currentTimeMillis() - (startTime > 0 ? (System.currentTimeMillis() - startTime) : 0);
            timer.start();
            running = true;
        }
    }

    private void stopStopwatch() {
        if (running) {
            timer.stop();
            running = false;
        }
    }

    private void resetStopwatch() {
        timer.stop();
        running = false;
        startTime = 0;
        timeLabel.setText("00:00:00:000");
    }

    private void updateDisplay() {
        long elapsed = System.currentTimeMillis() - startTime;
        int hours = (int) (elapsed / 3600000);
        int minutes = (int) ((elapsed % 3600000) / 60000);
        int seconds = (int) ((elapsed % 60000) / 1000);
        int milliseconds = (int) (elapsed % 1000);

        timeLabel.setText(String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, milliseconds));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StopwatchApp::new);
    }
}
