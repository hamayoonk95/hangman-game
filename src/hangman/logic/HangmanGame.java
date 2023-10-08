package hangman.logic;

/**
 This class handles the game logic for the Hangman Game, including tracking the number of incorrect guesses,
 updating the hidden word for correct guesses, keeping score, and determining whether the player has won or lost.
 */

public class HangmanGame {
    private String word;
    private String hiddenWord;
    private int incorrectGuesses;
    private int score;

    // Constructor to initialise the object with the given word
    public HangmanGame(String word) {
        this.word = word;
        // initialise hiddenWord with underscores for each character
        this.hiddenWord = "_ ".repeat(word.length());
        this.incorrectGuesses = 0;
        this.score = 0;
    }

    // returns the word
    public String getWord() {
        return word;
    }

    // returns the hiddenWord
    public String getHiddenWord() {
        return hiddenWord;
    }

    // increases incorrect guesses by 1
    public void incrementIncorrectGuesses() {
        incorrectGuesses++;
    }

    // returns incorrectGuessses
    public int getIncorrectGuesses() {
        return this.incorrectGuesses;
    }

    // returns the score
    public int getScore() {
        return score;
    }

    // updates the score
    public void updateScore(boolean correctGuess) {
        // if correctGuess then increase score by 10
        if(correctGuess) {
            score += 10;
        } else {
            // decrease score by 5
            score -= 5;
            if(score < 0) {
                score = 0;
            }
        }
    }

    // determines whether player has won by checking whether the word has any underscores left
    public boolean hasPlayerWon() {
        return !hiddenWord.contains("_");
    }

    // determines whether player has lost by checking if incorrectGuesses >= 6
    public boolean hasPlayerLost() {
        return incorrectGuesses >= 6;
    }

    // determines game is over whether hasPlayerWon or hasPlayerLost returns true.
    public boolean isGameOver() {
        return hasPlayerLost() || hasPlayerWon();
    }

    // this method takes a guessed character and checks if it appears in the word
    public boolean guessLetter(char guessedChar) {
        // a string builder to create a new hidden word based on player's guess
        StringBuilder displayBuilder = new StringBuilder();
        boolean correctGuess = false;
        // loop through the word
        for (int i = 0; i < word.length(); i++) {
            // if a character in the word matches the guessed character
            if (word.charAt(i) == guessedChar) {
                correctGuess = true;
                // add it to the string builder
                displayBuilder.append(guessedChar).append(' ');
            } else {
                // if character is not in the word, add the current character to the string builder
                displayBuilder.append(hiddenWord.charAt(i * 2)).append(' ');
            }
        }

        // update the hidden word with the build string
        hiddenWord = displayBuilder.toString().trim();
        return correctGuess;
    }
}