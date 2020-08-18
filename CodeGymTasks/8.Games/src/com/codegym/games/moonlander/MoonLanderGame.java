package com.codegym.games.moonlander;

import com.codegym.engine.cell.*;

public class MoonLanderGame extends Game {

    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;

    private Rocket rocket;
    private GameObject landscape;

    private boolean isUpPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
        showGrid(false);
    }

    @Override
    public void onTurn(int step) {
        rocket.move(isUpPressed, isLeftPressed, isRightPressed);
        drawScene();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (y < 0 || y >= HEIGHT || x < 0 || x >= WIDTH) {
            return;
        }
        super.setCellColor(x, y, color);
    }

    private void createGame() {

        this.isLeftPressed = this.isRightPressed = this.isUpPressed = false;

        setTurnTimer(50);
        createGameObjects();
        drawScene();
    }

    private void drawScene() {

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellColor(i, j, Color.GRAY);
            }
        }

        this.rocket.draw(this);
        this.landscape.draw(this);
    }

    private void createGameObjects() {

        this.rocket = new Rocket(WIDTH / 2, 0);
        this.landscape = new GameObject(0, 25, ShapeMatrix.LANDSCAPE);
    }

    @Override
    public void onKeyPress(Key key) {

        if (key == Key.UP) {
            isUpPressed = true;
        } else if (key == Key.LEFT) {
            isLeftPressed = true;
            isRightPressed = false;
        } else if (key == Key.RIGHT) {
            isRightPressed = true;
            isLeftPressed = false;
        }
        super.onKeyPress(key);
    }

    @Override
    public void onKeyReleased(Key key) {

        if (key == Key.UP) {
            isUpPressed = false;
        } else if (key == Key.LEFT) {
            isLeftPressed = false;
        } else if (key == Key.RIGHT) {
            isRightPressed = false;
        }
        super.onKeyReleased(key);
    }
}
