package br.univille;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class GameOver extends JPanel{
    
    public GameOver() {

        setPreferredSize(new DimensionUIResource(500, 275));
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
        String points = "Points: " + GameSettings.getScore();

        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 46));
        
        FontMetrics metrics = g.getFontMetrics();
        g.drawString(gameOver, (500/2) - (metrics.stringWidth(gameOver)/2), 110);
        g.drawString(points, (500/2) - (metrics.stringWidth(points)/2), 190);

    }
}
