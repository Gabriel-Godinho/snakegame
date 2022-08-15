package br.univille;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;

public class GameSettings extends JPanel implements KeyListener {

    private GameConstants height = GameConstants.SCREEN_HEIGHT;
    private GameConstants width = GameConstants.SCREEN_WIDTH;
    private GameConstants blockSize = GameConstants.BLOCK_SIZE;
    private GameConstants interval = GameConstants.INTERVAL;
    private int posX[] = new int[width.getNum() / blockSize.getNum()];
    private int posY[] = new int[height.getNum() / blockSize.getNum()];
    private int snakeSize = 6;
    private int foodX;
    private int foodY;
    private static int score;
    private boolean up, down, left, right;
    private Timer time;
    private Random rand = new Random();

    public GameSettings() {

        setPreferredSize(new DimensionUIResource(width.getNum(), height.getNum()));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        foodCoordinates();
        time = new Timer(interval.getNum(), e -> {
            catchFood();
            walk();
            repaint();});
        time.start();

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        drawSnake(g);
        drawFood(g);

    }

    private void drawSnake(Graphics g) {

        for (int i = 0; i < snakeSize; i++){
            if (i == 0) {
                g.setColor(new ColorUIResource(223, 240, 1));
                g.fillRect(posX[0], posY[0], blockSize.getNum(), blockSize.getNum());
            } else {
                g.setColor(Color.GREEN);
                g.fillRect(posX[i], posY[i], blockSize.getNum(), blockSize.getNum());
            }
        }

        if (posX[0] < 0 || posX[0] >= width.getNum()) {
            time.stop();
            gameOver();
        } else if (posY[0] < 0 || posY[0] >= height.getNum()) {
            time.stop();
            gameOver();
        } /* else {
            for (int i = 0; i < snakeSize; i++) {
                if (posX[0] == posX[i] && posY[0] == posY[i]) {
                    time.stop();
                }
            }
        } */
    }

    private void drawFood(Graphics g) {

        g.setColor(Color.RED);
        g.fillOval(foodX, foodY, blockSize.getNum(), blockSize.getNum());

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

    private void foodCoordinates() {

        foodX = rand.nextInt(width.getNum() / blockSize.getNum()) * blockSize.getNum();
        foodY = rand.nextInt(height.getNum() / blockSize.getNum()) * blockSize.getNum();

    }

    private void catchFood() {

        if (posX[0] == foodX && posY[0] == foodY) {
            snakeSize++;
            score++;
            foodCoordinates();
        }

    }

    private void gameOver() {

        JFrame frame = new JFrame();
        frame.setSize(500, 275);
        frame.setTitle("Game-Over");
        frame.setLocationRelativeTo(null);
        frame.add(new GameOver());
        frame.setVisible(true);

    }

    public static int getScore() {
        return score;
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
