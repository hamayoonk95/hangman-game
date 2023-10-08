package hangman.logic;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundHandler {
    // Static method to play sound, receives a sound name as parameter
    public static void playSound(String sound) {
        String path = "sounds/" + sound;
        File soundFile = new File(path);

        // Play sound if audio file is present and is supported audio file
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        // display error if file not found or unsupported file
        catch(IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.out.println(e);
        }
    }
}
