package hangman.gui;

import hangman.logic.HangmanGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GallowPanel extends JPanel {
    private HangmanGame hangmanGame;
    private int panelWidth;
    private int panelHeight;

    // Constructor that initialises the panel
    public GallowPanel(HangmanGame hangmanGame) {
        this.hangmanGame = hangmanGame;
        setPreferredSize(new Dimension(500, 200));
        setBackground(new Color(20,200,200));

        // component listener to resize the panel
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // set panel dimensions based on the new size and repaint the panel
                panelWidth = getWidth();
                panelHeight = getHeight();
                repaint();
            }
        });
    }

    // method to update the view
    public void updateView() {
        repaint();
    }

    // method to paint the component
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);

        // Calculate dimensions and positions based on panel size
        int baseWidth = panelWidth / 5;
        int baseHeight = panelHeight / 20;
        int postWidth = panelWidth / 60;
        int postHeight = (int) (panelHeight * 0.8);
        int beamWidth = panelWidth / 6;
        int beamHeight = panelHeight / 20;
        int ropeWidth = panelWidth / 110;

        int x = panelWidth / 2 - baseWidth;
        int y = panelHeight - baseHeight;

        // Draw gallows and rope
        g2.fillRect(x, y, baseWidth, baseHeight);
        g2.fillRect(x + baseWidth / 2 - postWidth / 2, y - postHeight, postWidth, postHeight);
        g2.fillRect(x + baseWidth / 2 - postWidth / 2, y - postHeight, beamWidth, beamHeight);
        g2.fillRect(x + baseWidth / 2 + beamWidth - ropeWidth, y - postHeight, ropeWidth, (int) (postHeight * 0.15));

        // change the line stroke width to 3
        g2.setStroke(new BasicStroke(3));

        // calculate hangman figure size
        int headRadius = panelWidth / 40;
        int bodyHeight = panelHeight / 8;
        int limbLength = panelWidth / 20;

        int numguesses = hangmanGame.getIncorrectGuesses();
        // Draw the Hangman figure based on the number of incorrect guesses
        // Hangman Head
        if (numguesses >= 1)
            g2.drawOval(x + baseWidth / 2 + beamWidth - headRadius, y - postHeight + (int) (postHeight * 0.15), headRadius * 2, headRadius * 2);
        // Hangman Body
        if (numguesses >= 2)
            g2.drawLine(x + baseWidth / 2 + beamWidth, y - postHeight + (int) (postHeight * 0.15) + headRadius * 2, x + baseWidth / 2 + beamWidth, y - postHeight + (int) (postHeight * 0.15) + headRadius * 2 + bodyHeight);
        // Hangman left arm
        if (numguesses >= 3)
            g2.drawLine(x + baseWidth / 2 + beamWidth, y - postHeight + (int) (postHeight * 0.15) + headRadius * 2, x + baseWidth / 2 + beamWidth - limbLength, y - postHeight + (int) (postHeight * 0.15) + headRadius * 2 + limbLength / 2);
        // Hangman right arm
        if (numguesses >= 4)
            g2.drawLine(x + baseWidth / 2 + beamWidth, y - postHeight + (int) (postHeight * 0.15) + headRadius * 2, x + baseWidth / 2 + beamWidth + limbLength, y - postHeight + (int) (postHeight * 0.15) + headRadius * 2 + limbLength / 2);
        // Hangman left leg
        if (numguesses >= 5)
            g2.drawLine(x + baseWidth / 2 + beamWidth, y - postHeight + (int) (postHeight * 0.15) + headRadius * 2 + bodyHeight, x + baseWidth / 2 + beamWidth - limbLength, y - postHeight + (int) (postHeight * 0.15) + headRadius * 2 + bodyHeight + limbLength / 2);
        // Hangman right leg
        if (numguesses >= 6)
            g2.drawLine(x + baseWidth / 2 + beamWidth, y - postHeight + (int) (postHeight * 0.15) + headRadius * 2 + bodyHeight, x + baseWidth / 2 + beamWidth + limbLength, y - postHeight + (int) (postHeight * 0.15) + headRadius * 2 + bodyHeight + limbLength / 2);
    }
}