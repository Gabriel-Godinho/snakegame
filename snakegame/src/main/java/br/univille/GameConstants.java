package br.univille;

public enum GameConstants {
    
    BLOCK_SIZE(20),
    INTERVAL(100),
    SCREEN_WIDTH(1000),
    SCREEN_HEIGHT(550);

    private int num;

    GameConstants(int num) {
        this.num = num;
    }

    public int getNum() {
        return this.num;
    }
}
