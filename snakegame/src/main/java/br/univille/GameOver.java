package br.univille;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class GameOver extends JPanel{
    
    public GameOver() {

        setBackground(Color.BLACK);
        repaint();

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        screen(g);

    }

    private void screen(Graphics g) {

        String gameOver = "Game-Over";
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 52));
        FontMetrics metrics = g.getFontMetrics();
        g.drawString(gameOver, (500/2) - (metrics.stringWidth(gameOver)/2), 137);

    }
}
