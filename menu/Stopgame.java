package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import chessBoard.*;

public class Stopgame {
    public Stopgame() {
        JFrame mainWindow = new JFrame("Stopgame");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainWindow.add(buttonPanel, BorderLayout.CENTER);


        JButton forfeitButton = new JButton("Forfeit");
        forfeitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(forfeitButton,(ChessBoard.getTurn()==0)?"Noir a gagné en forfait" : "Blanc a gagné en forfait");
            	
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    window.dispose();
                }
            }
        });
        buttonPanel.add(forfeitButton);


        JButton drawButton = new JButton("Draw");
        drawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show a pop-up window with "Yes" and "No" buttons
            	String message = ((ChessBoard.getTurn()==0)?"Blanc propose une égalité" : "Noir propose une égalité");
                int choice = JOptionPane.showOptionDialog(mainWindow, message, "Acceptez-vous la proposition ?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (choice == JOptionPane.YES_OPTION) {
                    Window[] windows = Window.getWindows();
                    for (Window window : windows) {
                        window.dispose();
                    }
                } else if (choice == JOptionPane.NO_OPTION) {
                }
            }
        });
        buttonPanel.add(drawButton);

        mainWindow.setSize(300, 200);
        mainWindow.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - mainWindow.getWidth(), Toolkit.getDefaultToolkit().getScreenSize().height - mainWindow.getWidth());
        
        mainWindow.setVisible(true);
    }
}
