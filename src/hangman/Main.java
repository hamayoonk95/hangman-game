package hangman;

import hangman.gui.HangmanGUI;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static ArrayList<String> targetWords = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        // scanner to load words from file into targetWords array
        Scanner in = new Scanner(new File("wordlist.txt"));
        while(in.hasNext()){
            targetWords.add(in.next());
        }

        // startGame method to initialise the game GUI
        startGame();
    }

    // Method gets a random word from the targetWords array
    public static String getWord(){
        Random r = new Random();
        String word = targetWords.get(r.nextInt(targetWords.size()));
        System.out.println("The target word is: " + word);
        return word;
    }

    // Method to initialise a new instance of HangmanGUI and passing the random word as paramter
    public static void startGame() {
        SwingUtilities.invokeLater(() -> new HangmanGUI(getWord()));
    }
}
