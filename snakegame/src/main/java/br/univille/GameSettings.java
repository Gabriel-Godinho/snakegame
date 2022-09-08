package br.univille;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.DimensionUIResource;

public class GameSettings extends JPanel implements KeyListener {

    private GameConstants height = GameConstants.SCREEN_HEIGHT;
    private GameConstants width = GameConstants.SCREEN_WIDTH;
    private GameConstants blockSize = GameConstants.BLOCK_SIZE;
    private GameConstants interval = GameConstants.INTERVAL;
    private int posX[] = new int[width.getNum() * blockSize.getNum()];
    private int posY[] = new int[height.getNum() * blockSize.getNum()];
    private int snakeSize = 6;
    private int foodX;
    private int foodY;
    private static int score;
    private boolean up, down, left, right;
    private Timer timer;
    private Random rand = new Random();

    public GameSettings() {

        setPreferredSize(new DimensionUIResource(width.getNum(), height.getNum()));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        foodCoordinates();
        timer = new Timer(interval.getNum(), e -> {
            reachWall();
            walk();
            catchFood();
            repaint();});
        timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        drawFood(g);
        drawSnake(g);

    }

    private void drawSnake(Graphics g) {

        for (int i = 0; i < snakeSize; i++){
            g.setColor(Color.GREEN);
            g.fillRect(posX[i], posY[i], blockSize.getNum(), blockSize.getNum());
        }
        
    }

    private void drawFood(Graphics g) {

        g.setColor(Color.RED);
        g.fillRect(foodX, foodY, blockSize.getNum(), blockSize.getNum());

    }

    private void walk() {

        for (int i = snakeSize; i > 0; i--) {
            posX[i] = posX[i - 1];
            posY[i] = posY[i - 1];
        }

        if (up) {
            posY[0] -= blockSize.getNum();
            gameOver();
        } else if (down) {
            posY[0] += blockSize.getNum();
            gameOver();
        } else if (left) {
            posX[0] -= blockSize.getNum();
            gameOver();
        } else if (right) {
            posX[0] += blockSize.getNum();
            gameOver();
        }

    }

    private void foodCoordinates() {

        foodX = rand.nextInt(width.getNum() / blockSize.getNum()) * blockSize.getNum();
        foodY = rand.nextInt(height.getNum() / blockSize.getNum()) * blockSize.getNum();

    }

    private void reachWall() {

        if (posX[0] < 0) {
            posX[0] = blockSize.getNum() * 67;
        } else if (posX[0] > 1000 + 10) {
            posX[0] = -15;
        }

        if (posY[0] < 0) {
            posY[0] = blockSize.getNum() * 34;
        } else if (posY[0] > 500 + 10) {
            posY[0] = -15;
        }

    }

    private void catchFood() {

        if (posX[0] == foodX && posY[0] == foodY) {
            foodCoordinates();
            snakeSize++;
            score++;
        }

    }

    private void gameOver() {

        for (int i = 0; i <= snakeSize; i++) {
            if (i == 0) {
                continue;
            } else if (posX[0] == posX[i] && posY[0] == posY[i]) {
                timer.stop();
                gameOverView();
            }
        }

    }

    private void gameOverView() {

        JFrame frame = new JFrame();
        frame.setSize(500, 275);
        frame.setTitle("Game-Over");
        frame.setLocationRelativeTo(null);
        frame.add(new GameOver(), "Center");
        frame.setIconImage(new ImageIcon(getClass().getResource("./img/dead.png")).getImage());
        frame.setVisible(true);

    }

    public static int getScore() {
        return score;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        walk();
        
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            if (!down) {
                up = true;
                down = false;
                right = false;
                left = false;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            if (!up) {
                down = true;
                up = false;
                right = false;
                left = false;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            if (!right) {
                left = true;
                right = false;
                up = false;
                down = false;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
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
