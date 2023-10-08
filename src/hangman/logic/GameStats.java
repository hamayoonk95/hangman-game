package hangman.logic;

public class GameStats {
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;

    // Constructor to initialise variables
    public GameStats() {
        gamesPlayed = 0;
        gamesWon = 0;
        gamesLost = 0;
    }

    // Method to increment games played
    public void incrementGamesPlayed() {
        gamesPlayed++;
    }

    // Method to increment games won
    public void incrementGamesWon() {
        gamesWon++;
    }

    // Method to increment games lost
    public void incrementGamesLost() {
        gamesLost++;
    }

    // Method to get total games played
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    // Method to get total games won
    public int getGamesWon() {
        return gamesWon;
    }

    // Method to get total games lost
    public int getGamesLost() {
        return gamesLost;
    }
}
