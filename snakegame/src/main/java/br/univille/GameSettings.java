package br.univille;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GameSettings extends JPanel implements KeyListener {

    private GameConstants height = GameConstants.SCREEN_HEIGHT;
    private GameConstants width = GameConstants.SCREEN_WIDTH;
    private GameConstants blockSize = GameConstants.BLOCK_SIZE;
    private GameConstants interval = GameConstants.INTERVAL;
    private int posX[] = new int[width.getNum() / 2];
    private int posY[] = new int[height.getNum() / 2];
    private int snakeSize = 6;
    private int blockX;
    private  int blockY;
    private Timer time;
    private Random rand = new Random();

    public GameSettings() {

        setBackground(Color.LIGHT_GRAY);
        setFocusable(true);
        addKeyListener(this);
        time = new Timer(interval.getNum(), e -> {walk(); repaint();});
        time.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSnake(g);
    }

    private void drawSnake(Graphics g) {
        for (int i = 0; i < snakeSize; i++){
            if (i == 0) {
                g.setColor(Color.GREEN);
                g.fillRect(posX[0], posY[0], blockSize.getNum(), blockSize.getNum());
            } else {
                g.setColor(Color.RED);
                g.fillRect(posX[i], posY[i], blockSize.getNum(), blockSize.getNum());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            posY[0] -= blockSize.getNum(); 
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            posY[0] += blockSize.getNum();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            posX[0] -= blockSize.getNum();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            posX[0] += blockSize.getNum();
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    private void walk() {
        for (int i = snakeSize; i > 0; i--) {
            posX[i] = posX[i - 1];
            posY[i] = posY[i - 1];
        }
    }
}
