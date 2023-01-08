package br.univille;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.util.Objects;

public class GameScreen extends JFrame{

    public GameScreen() {
        setSize(1016, 550);
        add(new GameSettings(), "Center");
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIcon();
        setVisible(true);
    }

    private void setIcon() {
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("img/snake.png"))).getImage());
    }

}
