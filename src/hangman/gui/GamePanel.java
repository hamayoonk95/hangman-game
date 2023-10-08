package hangman.gui;

import hangman.logic.HangmanGame;
import hangman.logic.SoundHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    HangmanGUI parent;
    GallowPanel gallowPanel;
    JLabel displayWord;
    HangmanGame hangmanGame;
    LetterButtonPanel letterPanel;
    JLabel scoreLabel;

    // Constructor to initialise the panel
    GamePanel(String word, HangmanGUI parent) {
        // reference to HangmanGUI parent to call some of its methods from this class
        this.parent = parent;
        // initialise new HangmanGame class with word as the parameter which handles the game logic
        this.hangmanGame = new HangmanGame(word);

        // set layout and background color for the panel
        setBackground(new Color(20,200,200));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // score label to display score at the top of the screen
        scoreLabel = new JLabel("Score: " + hangmanGame.getScore());
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 16));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(scoreLabel);

        // initialise gallowPanel and add to the panel
        gallowPanel = new GallowPanel(this.hangmanGame);
        gallowPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(gallowPanel);

        // initialise a Label for the hidden word and add it to the panel
        displayWord = new JLabel(hangmanGame.getHiddenWord());
        displayWord.setHorizontalAlignment(SwingConstants.CENTER);
        displayWord.setFont(new Font("Serif", Font.BOLD, 24));
        displayWord.setForeground(Color.BLACK);
        displayWord.setAlignmentX(Component.CENTER_ALIGNMENT);
        displayWord.setBorder(BorderFactory.createEmptyBorder(10,0,20,0));
        add(displayWord);

        // initialise letter button panel
        letterPanel = new LetterButtonPanel(this);
        letterPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(letterPanel);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        // Get the button that was clicked
        JButton clickedButton = (JButton) ae.getSource();
        // disable the button
        clickedButton.setEnabled(false);

        // get the letter from the clicked button's text
        String guessedChar = ae.getActionCommand().toLowerCase();
        // call guessLetter method in hangmanGame logic handler to check if the letter was correct or not
        boolean correctGuess = hangmanGame.guessLetter(guessedChar.charAt(0));
        // call updateScore to determine the score
        hangmanGame.updateScore(correctGuess);
        // update the score text on scoreLabel based on new score
        scoreLabel.setText("Score: " + hangmanGame.getScore());

        // if guess was not correct,
        if (!correctGuess) {
            // increase number of incorrect guesses in hangman class
            hangmanGame.incrementIncorrectGuesses();
            // play sound for wrong guess
            SoundHandler.playSound("wrong.wav");
            // call updateView on gallowPanel which repaints the graphics to show updated drawing
            gallowPanel.updateView();
        } else {
            //if guess was correct, update the displayed hiddenWord
            displayWord.setText(hangmanGame.getHiddenWord().trim());
            // play sound for correct answer
            SoundHandler.playSound("correct.wav");
        }

        // if gameOver,
        if (hangmanGame.isGameOver()) {
            // disable all the letter buttons
            letterPanel.disableAllButtons();

            // if player has won
            if (hangmanGame.hasPlayerWon()) {
                // play gameWon sound and display congratulation message
                SoundHandler.playSound("gamewon.wav");
                JOptionPane.showMessageDialog(this, "Congratulations! You won!", "Game Over",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            // if player's lost, then display the correct word
            else if (hangmanGame.hasPlayerLost()) {
                SoundHandler.playSound("gamelost.wav");
                JOptionPane.showMessageDialog(this, "Game over! You lost! The word was " + hangmanGame.getWord() + ".",
                        "Game Over", JOptionPane.INFORMATION_MESSAGE);
            }

            // call the updateGameStats method of HangmanGUI parent and pass the player's gameOver status to determine whether to increase wins or losses in Game statistics
            parent.updateGameStats(hangmanGame.hasPlayerWon());
            // display the game statistics after each game
            parent.displayGameStats();


            // Option pane to ask users if they want to play another game or quit!
            int option = JOptionPane.showOptionDialog(this, "Would you like to play again?",
                    "Play Again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new String[]{"Yes", "No"}, "No");

            // if option is YES,
            if (option == JOptionPane.YES_OPTION) {
                // close the Optionpane and call the startNewGame in HangmanGUI class
                this.setVisible(false);
                parent.startNewGame();
            } else {
                // else exit the system
                System.exit(0);
            }
        }
    }
}
