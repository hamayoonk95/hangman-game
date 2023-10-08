package hangman.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PlayPanel extends JPanel {
    JButton playButton, exitButton;
    // Constructor to initialise PlayPanel
    PlayPanel(HangmanGUI parent) {
        // set layout, background and grid constraints for the Panel
        setLayout(new GridBagLayout());
        setBackground(new Color(20,200,200));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5,0,5,0);

        // Label for title of the game on PlayPanel
        JLabel titleLabel = new JLabel("Hangman Game");
        titleLabel.setFont(new Font("Algerian", Font.BOLD, 30));
        add(titleLabel, constraints);

        // Display image of a Gallow on the play panel
        ImageIcon hangmanIcon = new ImageIcon("assets/gallow.png"); // replace with your image path
        Image scaledHangmanImage = hangmanIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledHangmanIcon = new ImageIcon(scaledHangmanImage);
        JLabel hangmanLabel = new JLabel(scaledHangmanIcon);
        constraints.gridy = 1;
        constraints.insets = new Insets(0,0,10,0);
        add(hangmanLabel, constraints);

        // set contraints for the play button on the panel
        constraints.gridy = 2;
        constraints.insets = new Insets(10,0,5,0);
        // initialise the playButton
        playButton = new JButton("Play");
        // add actionListener to the playButton that removes itself from the Frame and calls startNewGame from HangmanGUI class
        playButton.addActionListener(e -> {
            parent.remove(this);
            parent.startNewGame();
            parent.validate();
        });
        add(playButton, constraints);

        // set grid constraints for the exit buttons
        constraints.gridy = 3;
        constraints.insets = new Insets(5,0,5,0);
        // initialise exit buttons
        exitButton = new JButton("Exit");
        // add ActionListener to the exit button to close the program when clicked
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
        add(exitButton, constraints);

    }
}
