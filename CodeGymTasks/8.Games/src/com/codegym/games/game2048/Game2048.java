package com.codegym.games.game2048;

import com.codegym.engine.cell.*;

public class Game2048 extends Game {

    private static final int SIDE = 4;

    private int[][] gameField = new int[SIDE][SIDE];

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }

    private void createGame() {
        
        createNewNumber();
        createNewNumber();
    }
    
    private void drawScene() {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                setCellColoredNumber(i, j, gameField[i][j]);
            }
        }
    }
    
    private void createNewNumber() {
        
        int x, y;
        do {
        
            x = getRandomNumber(SIDE);
            y = getRandomNumber(SIDE);
            
        } while (gameField[x][y] != 0);
        
        gameField[x][y] = getRandomNumber(10) == 9 ? 4 : 2;
    }

    private void setCellColoredNumber(int y, int x, int value) {

        Color color = getColorByValue(value);
        if (value == 0) {
            setCellValueEx(x, y, color, "");
        } else {
            setCellValueEx(x, y, color, Integer.toString(value));
        }
    }

    private Color getColorByValue(int value) {

        switch (value) {

            case 0:
                return Color.WHITE;
            case 2:
                return Color.PINK;
            case 4:
                return Color.VIOLET;
            case 8:
                return Color.BLUE;
            case 16:
                return Color.LIGHTBLUE;
            case 32:
                return Color.GREEN;
            case 64:
                return Color.LIGHTGREEN;
            case 128:
                return Color.ORANGE;
            case 256:
                return Color.YELLOWGREEN;
            case 512:
                return Color.RED;
            case 1024:
                return Color.MAGENTA;
            case 2048:
                return Color.DARKMAGENTA;
        }

        return Color.NONE;
    }
}
