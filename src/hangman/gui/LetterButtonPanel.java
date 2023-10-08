package hangman.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LetterButtonPanel extends JPanel {
    JButton[] letterButtons;

    // constructor to initialise buttons for on-screen keyboard
    public LetterButtonPanel(ActionListener actionListener) {
        // initialise the array with 26 JButtons
        letterButtons = new JButton[26];
        int i = 0;
        //
        for (char letter = 'A'; letter <= 'Z'; letter++, i++) {
            // initialise the buttons with letters from A to Z
            letterButtons[i] = new JButton(String.valueOf(letter));
            // add the actionListener from GamePanel to each button
            letterButtons[i].addActionListener(actionListener);
        }

        // set layout for the buttons and add them to the panel
        setLayout(new GridLayout(3, 7));
        for (i = 0; i < letterButtons.length; i++) {
            add(letterButtons[i]);
        }
    }

    // Method to disable all buttons when game is Over
    public void disableAllButtons() {
        for(JButton button : letterButtons) {
            button.setEnabled(false);
        }
    }
}