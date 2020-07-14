package com.codegym.games.snake;

import com.codegym.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";

    private List<GameObject> snakeParts = new ArrayList<>();

    public Snake(int x, int y) {

        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public void draw(Game game) {

        GameObject object = snakeParts.get(0);
        game.setCellValue(object.x, object.y, HEAD_SIGN);

        for (int i = 1; i < snakeParts.size(); i++) {
            object = snakeParts.get(i);
            game.setCellValue(object.x, object.y, BODY_SIGN);
        }
    }
}
