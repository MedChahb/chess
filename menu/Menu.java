package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener {
	private String whitePlayerName, blackPlayerName, mode;
	
	private boolean isSubmitted = false;

	private JTextField whiteName, blackName;
	JRadioButton mode1, mode2;
	private JButton submit;

    public Menu() {
        // Appeler le constructeur de la classe JFrame
        super("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);

        // Créer un panneau pour les éléments d'interface utilisateur
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Ajouter les éléments d'interface utilisateur
        panel.add(new JLabel("White player name:"));
        whiteName = new JTextField();
        panel.add(whiteName);

        panel.add(new JLabel("Black player name:"));
        blackName = new JTextField();
        panel.add(blackName);

        panel.add(new JLabel("Game mode:"));
        mode1 = new JRadioButton("Standard");
        mode2 = new JRadioButton("Pieces Féériques");
        ButtonGroup group = new ButtonGroup();
        group.add(mode1);
        group.add(mode2);
        JPanel modePanel = new JPanel();
        modePanel.add(mode1);
        modePanel.add(mode2);
        panel.add(modePanel);

        submit = new JButton("Submit");
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit.addActionListener(this);
        panel.add(Box.createVerticalGlue());
        panel.add(submit);
        setLayout(new GridBagLayout());
        add(panel);

        // Afficher la fenêtre
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Fermer la fenêtre lorsque le bouton "Submit" est cliqué
        if (e.getSource() == submit) {
        	this.whitePlayerName = whiteName.getText();
        	this.blackPlayerName = blackName.getText();
        	this.isSubmitted = true;
        	if(mode2.isSelected()) {
        		this.mode = "Pieces Féériques";
        		JOptionPane.showMessageDialog(this, "Description du GameMode à ajouter ici");
        	}
        	else {this.mode = "Standard";} // si aucun des boutons radio est séléctioné, alors standard
            dispose();
        }
    }
    
    public boolean isSubmitted() { return this.isSubmitted;}

    public String getWhitePlayerName() { return this.whitePlayerName;}
    public String getBlackPlayerName() { return this.blackPlayerName;}
    public String getMode() { return this.mode;}


}

