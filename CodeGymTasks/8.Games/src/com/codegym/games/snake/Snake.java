package com.codegym.games.snake;

import com.codegym.engine.cell.*;
import org.omg.PortableInterceptor.DISCARDING;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";

    private Direction direction = Direction.LEFT;

    public boolean isAlive = true;

    private List<GameObject> snakeParts = new ArrayList<>();

    public Snake(int x, int y) {

        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public void setDirection(Direction d) {

        GameObject one = snakeParts.get(0),
                two =  snakeParts.get(1);

        if (direction == Direction.LEFT && d == Direction.RIGHT) {
            return;
        } else if (direction == Direction.RIGHT && d == Direction.LEFT) {
            return;
        } else if (direction == Direction.UP && d == Direction.DOWN) {
            return;
        } else if (direction == Direction.DOWN && d == Direction.UP) {
            return;
        } else if (direction == Direction.LEFT || direction == Direction.RIGHT) {

            if (one.x == two.x) {
                return;
            }

        } else if (direction == Direction.UP || direction == Direction.DOWN) {

            if (one.y == two.y) {
                return;
            }
        }

        this.direction = d;
    }

    public void draw(Game game) {

        GameObject object = snakeParts.get(0);
        Color color = isAlive ? Color.BLACK : Color.RED;
        game.setCellValueEx(object.x, object.y, Color.NONE, HEAD_SIGN, color, 75);

        for (int i = 1; i < snakeParts.size(); i++) {
            object = snakeParts.get(i);
            game.setCellValueEx(object.x, object.y, Color.NONE, BODY_SIGN, color, 75);
        }
    }

    public void move(Apple apple) {

        GameObject newHead = this.createNewHead();
        if (newHead.x < 0 || newHead.y < 0 || newHead.x >= SnakeGame.WIDTH || newHead.y >= SnakeGame.HEIGHT) {
            this.isAlive = false;
            return;
        }

        if (checkCollision(newHead)) {
            this.isAlive = false;
            return;
        }

        snakeParts.add(0, newHead);
        if (newHead.x == apple.x && newHead.y == apple.y) {
            apple.isAlive = false;
        } else{
            this.removeTail();
        }
    }

    public GameObject createNewHead() {

        GameObject snakeHead = snakeParts.get(0);
        int x = snakeHead.x,
                y = snakeHead.y;

        if (direction == Direction.LEFT) {
            x -= 1;
        } else if  (direction == Direction.DOWN) {
            y += 1;
        } else if (direction == Direction.RIGHT) {
            x += 1;
        } else if (direction == Direction.UP) {
            y -= 1;
        }

        return new GameObject(x, y);
    }

    public void removeTail() {

        snakeParts.remove(snakeParts.size() - 1);
    }

    public boolean checkCollision(GameObject gameObject) {

        for (GameObject segment : snakeParts) {
            if (segment.x == gameObject.x && segment.y == gameObject.y) {
                return true;
            }
        }

        return false;
    }

    public int getLength() {
        return this.snakeParts.size();
    }
}
