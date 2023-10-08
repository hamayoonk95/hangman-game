package hangman.gui;

import hangman.Main;
import hangman.logic.GameStats;

import javax.swing.*;
import java.awt.*;

public class HangmanGUI extends JFrame {
    PlayPanel playPanel;
    GamePanel gamePanel;
    GameStats gameStats;

    // Constructor to initialise GUI and components
    public HangmanGUI(String word) {
        // sets title, layout, windows size and location
        setTitle("Hangman Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(600, 400));
        setLocation(300,150);

        // create a playPanel and pass this object as parameter
        playPanel = new PlayPanel(this);
        // add playPanel to the center Frame
        add(playPanel, BorderLayout.CENTER);

        // create a gamePanel and pass the word and this object as parameters
        gamePanel = new GamePanel(word, this);

        // create a gameStats panel
        gameStats = new GameStats();

        // pack and make the frame visible
        pack();
        setVisible(true);
    }

    // Method to start a new game
    public void startNewGame() {
        // get a new word from the main class
        String newWord = Main.getWord();
        // create a new GamePanel object with new word and this frame
        gamePanel = new GamePanel(newWord, this);
        // add the gamePanel to the center of the frame
        add(gamePanel, BorderLayout.CENTER);
        // validate the frame
        validate();
        // increment the num of games played in gameStats class
        gameStats.incrementGamesPlayed();
    }

    // Method to update the game statistics on whether player has won or lost
    public void updateGameStats(boolean playerWon) {
        if(playerWon) {
            gameStats.incrementGamesWon();
        } else {
            gameStats.incrementGamesLost();
        }
    }

    // Method to display game statistics in a message dialogue
    public void displayGameStats() {
        String message = String.format("Games Played: %d \nGames Won: %d \nGames Lost: %d", gameStats.getGamesPlayed(), gameStats.getGamesWon(), gameStats.getGamesLost());

        JOptionPane.showMessageDialog(this, message, "Game Statistics", JOptionPane.INFORMATION_MESSAGE);
    }
}