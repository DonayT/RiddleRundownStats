import javax.swing.*;
import java.awt.*;

public class MyRRApp {

    private static void createAndShowGUI() {
        // Window name and setup
        JFrame frame = new JFrame("Riddle Rundown Statistics");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Label on window
        JLabel label = new JLabel("Riddle Rundown Statistics");
        frame.getContentPane().add(label);

        // For window szie
        Dimension sz = Toolkit.getDefaultToolkit().getScreenSize();
        int wHeight = sz.height * (3);
        int wWidth = sz.width * (3);
        wHeight = wHeight / 4;
        wWidth = wWidth / 4;
        System.out.println("Screen size: " + sz.width + " x " + sz.height);
        System.out.println("Screen size: " + wHeight + " x " + wWidth);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setSize(wWidth, wHeight);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}