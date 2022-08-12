package br.univille;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameSettings extends JPanel {

    private GameConstants height = GameConstants.SCREEN_HEIGHT;
    private GameConstants width = GameConstants.SCREEN_WIDTH;
    private GameConstants blockSize = GameConstants.BLOCK_SIZE;
    private GameConstants interval = GameConstants.INTERVAL;
    private int snakeSize = 3;

    public GameSettings() {

        setBackground(Color.LIGHT_GRAY);
        setFocusable(true);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSnake(g);
    }

    private void drawSnake(Graphics g) {
        for (int i = 0; i < snakeSize; i++) {
            if (i == 0) {
                g.setColor(Color.RED);
                g.fillRect(height.getNum(), width.getNum(), blockSize.getNum(), blockSize.getNum());
            } else if (i > 0) {
                g.setColor(Color.GREEN);
                g.fillRect(height.getNum(), width.getNum(), blockSize.getNum(), blockSize.getNum());
            }
        }
    }
}
