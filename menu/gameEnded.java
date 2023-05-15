package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class gameEnded {
    public gameEnded(String message) {
        // Create the information window
        JFrame infoWindow = new JFrame("Partie terminé");
        infoWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Create a panel to hold the content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Use BoxLayout with vertical orientation
        infoWindow.add(contentPanel);
        
        // Create the label
        JLabel messageLabel = new JLabel(message);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label
        contentPanel.add(messageLabel);
        
        // Create the OK button
        JButton okButton = new JButton("ok");
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close all windows
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    window.dispose();
                }
            }
        });
        contentPanel.add(Box.createVerticalGlue()); // Add vertical glue to push the button to the bottom
        contentPanel.add(okButton);
        
        // Set the size and visibility of the information window
        infoWindow.setSize(300, 150);
        infoWindow.setLocationRelativeTo(null);
        infoWindow.setVisible(true);
    }
}
