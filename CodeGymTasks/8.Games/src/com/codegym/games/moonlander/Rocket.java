package com.codegym.games.moonlander;

public class Rocket extends GameObject {

    private double speedX = 0;
    private double speedY = 0;
    private double boost = 0.05;

    public Rocket(double x, double y) {
        super(x, y, ShapeMatrix.ROCKET);
    }

//    public void move() {
//        speedY += boost;
//        y += speedY;
//    }

    public void move(boolean isUpPressed, boolean isLeftPressed, boolean isRightPressed) {

        if (isUpPressed) {
            speedY -= boost;
        } else {
            speedY += boost;
        }
        y += speedY;

        if (isLeftPressed) {
            speedX -= boost;
            x += speedX;
        } else if (isRightPressed) {
            speedX += boost;
            x += speedX;
        }

        checkBorders();
    }

    private void checkBorders() {

        int width = ShapeMatrix.ROCKET[0].length / 2;

        if (x < 0) {
            x = 0;
            speedX = 0;
        } else if ((x + width) > MoonLanderGame.WIDTH) {
            x = MoonLanderGame.WIDTH - width;
            speedX = 0;
        }

        if (y < 0) {
            y = 0;
            speedY = 0;
        }
    }
}
