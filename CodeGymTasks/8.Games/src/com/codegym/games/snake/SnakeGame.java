package com.codegym.games.snake;

import com.codegym.engine.cell.*;

public class SnakeGame extends Game {

    public static final int WIDTH =  15;
    public static final int HEIGHT = 15;
    private static final int GOAL = 28;

    private Snake snake;
    private Apple apple;
    private int turnDelay;
    private int score;
    private boolean isGameStopped;

    @Override
    public void initialize() {

        this.setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {

        isGameStopped = false;
        createNewApple();
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        turnDelay = 300;
        score = 0;

        drawScene();
        setTurnTimer(turnDelay);
        setScore(score);
//        Apple apple = new Apple(7, 7);
//        apple.draw(this);
    }

    private void drawScene() {

        int i, j;
        for (i = 0; i < WIDTH; i++) {
            for (j = 0; j < HEIGHT; j++) {
                this.setCellValueEx(i, j, Color.DARKGREEN, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }

    @Override
    public void onTurn(int step) {
//        super.onTurn(step);

        if (!snake.isAlive) {
            gameOver();
        }

        if (!apple.isAlive) {
            score += 5;
            setScore(score);
            turnDelay -= 10;
            setTurnTimer(turnDelay);
            createNewApple();
        }

        if (snake.getLength() > GOAL) {
            win();
        }

        snake.move(apple);
        this.drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
//        super.onKeyPress(key);
        switch (key) {
            case LEFT:
                snake.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                snake.setDirection(Direction.RIGHT);
                break;
            case DOWN:
                snake.setDirection(Direction.DOWN);
                break;
            case UP:
                snake.setDirection(Direction.UP);
                break;
            case SPACE:
                if (isGameStopped) {
                    createGame();
                }
                break;
        }
    }

    private void createNewApple() {

        do {
            int x = this.getRandomNumber(WIDTH);
            int y = this.getRandomNumber(HEIGHT);
            apple = new Apple(x, y);
        } while (snake.checkCollision(apple));
    }

    private void gameOver() {
        this.isGameStopped = true;
        this.stopTurnTimer();
        this.showMessageDialog(Color.RED, "GAME OVER",  Color.WHITE, 75);
    }

    private void win() {
        this.isGameStopped = true;
        this.stopTurnTimer();;
        this.showMessageDialog(Color.GREEN, "YOU WIN", Color.WHITE, 75);
    }
}
