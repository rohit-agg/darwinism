package com.codegym.games.snake;

import com.codegym.engine.cell.*;

import java.util.Stack;

public class SnakeGame extends Game {

    public static final int WIDTH =  15;
    public static final int HEIGHT = 15;

    private Snake snake;

    @Override
    public void initialize() {

        this.setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {

        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        drawScene();
        Stack abc = new Stack();
//        Apple apple = new Apple(7, 7);
//        apple.draw(this);
    }

    private void drawScene() {

        int i, j;
        for (i = 0; i < WIDTH; i++) {
            for (j = 0; j < HEIGHT; j++) {
                this.setCellColor(i, j, Color.GREEN);
            }
        }
        snake.draw(this);
    }
}
