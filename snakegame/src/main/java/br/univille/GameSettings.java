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
    private int posX[] = new int[width.getNum() / blockSize.getNum()];
    private int posY[] = new int[height.getNum() / blockSize.getNum()];
    private int snakeSize = 6;
    private boolean up, down, left, right;
    private int blockX;
    private int blockY;
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

        drawFood(g);

        for (int i = 0; i < snakeSize; i++){
            if (i == 0) {
                g.setColor(Color.GREEN);
                g.fillRect(posX[0], posY[0], blockSize.getNum(), blockSize.getNum());
            } else {
                g.setColor(Color.RED);
                g.fillRect(posX[i], posY[i], blockSize.getNum(), blockSize.getNum());
            }
        }

        if (posX[0] < 0 || posX[0] >= width.getNum()) {
            time.stop();
        } else if (posY[0] < 0 || posY[0] >= height.getNum()) {
            time.stop();
        } /* else {
            for (int i = 0; i < snakeSize; i++) {
                if (posX[0] == posX[i] && posY[0] == posY[i]) {
                    time.stop();
                }
            }
        } */
    }

    private void walk() {

        for (int i = snakeSize; i > 0; i--) {
            posX[i] = posX[i - 1];
            posY[i] = posY[i - 1];
        }

        if (up) {
            posY[0] -= blockSize.getNum(); 
        } else if (down) {
            posY[0] += blockSize.getNum();
        } else if (left) {
            posX[0] -= blockSize.getNum();
        } else if (right) {
            posX[0] += blockSize.getNum();
        }

    }

    private void drawFood(Graphics g) {
        blockX = rand.nextInt(width.getNum() / blockSize.getNum());
        blockY = rand.nextInt(height.getNum() / blockSize.getNum());

        g.setColor(Color.yellow);
        g.fillRect(blockX, blockY, blockSize.getNum(), blockSize.getNum());
    }

    @Override
    public void keyPressed(KeyEvent e) {

        walk();
        
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (!down) {
                up = true;
                down = false;
                right = false;
                left = false;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (!up) {
                down = true;
                up = false;
                right = false;
                left = false;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (!right) {
                left = true;
                right = false;
                up = false;
                down = false;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (!left) {
                right = true;
                left = false;
                up = false;
                down = false;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
