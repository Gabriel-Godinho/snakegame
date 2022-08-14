package br.univille;

import javax.swing.JFrame;

public class GameScreen extends JFrame{
     
    public GameScreen() {

        setSize(1000, 550);
        add(new GameSettings(), "Center");
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
