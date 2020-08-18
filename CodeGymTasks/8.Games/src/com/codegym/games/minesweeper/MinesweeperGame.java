package com.codegym.games.minesweeper;

import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private int countFlags;
    private int countClosedTiles = SIDE * SIDE;
    private boolean isGameStopped;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[x][y] = new GameObject(x, y, isMine);
                setCellColor(y, x, Color.ORANGE);
                setCellValue(y, x, "");
            }
        }

        countMineNeighbors();
        countFlags = countMinesOnField;
//        isGameStopped = false;
        score = 0;
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
            for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (gameField[x][y] == gameObject) {
                    continue;
                }
                result.add(gameField[x][y]);
            }
        }
        return result;
    }

    private void countMineNeighbors() {

        List<GameObject> neighbors;
        int minedNeighbors;

        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {

                if (gameField[i][j].isMine) {
                    continue;
                }

                neighbors = getNeighbors(gameField[i][j]);
                minedNeighbors = 0;
                for (GameObject neighbor : neighbors) {
                    minedNeighbors += neighbor.isMine ? 1 : 0;
                }
                gameField[i][j].countMineNeighbors = minedNeighbors;
            }
        }
    }

    private void openTile(int i, int j) {

        if (isGameStopped) {
            return;
        } else if (gameField[i][j].isOpen) {
            return;
        } else if (gameField[i][j].isFlag) {
            return;
        }

        if (gameField[i][j].isMine) {
            setCellValueEx(j, i, Color.RED, MINE);
            gameOver();
        } else {

            countClosedTiles--;
            gameField[i][j].isOpen = true;
            score += 5;
            setScore(score);
            setCellColor(j, i, Color.GREEN);
            if (gameField[i][j].countMineNeighbors == 0) {

                setCellValue(j, i, "");
                for (GameObject o : getNeighbors(gameField[i][j])) {
                    if (!o.isOpen) {
                        openTile(o.x, o.y);
                    }
                }

            } else {
                setCellNumber(j, i, gameField[i][j].countMineNeighbors);
            }
        }

        if (countClosedTiles == countMinesOnField && !gameField[i][j].isMine) {
            win();
        }
    }

    private void markTile(int x, int y) {

        if (isGameStopped) {
            return;
        } else if (gameField[x][y].isOpen) {
            return;
        } else if (countFlags == 0 && !gameField[x][y].isFlag) {
            return;
        }

        if (gameField[x][y].isFlag) {

            gameField[x][y].isFlag = false;
            countFlags++;
            setCellValue(y, x, "");
            setCellColor(y, x, Color.ORANGE);

        } else {

            gameField[x][y].isFlag = true;
            countFlags--;
            setCellValue(y, x, FLAG);
            setCellColor(y, x, Color.YELLOW);
        }
    }

    private void gameOver() {

        isGameStopped = true;
        showMessageDialog(Color.RED, "Game Over", Color.WHITE, 100);
    }

    private void win() {

        isGameStopped = true;
        showMessageDialog(Color.GREEN, "You Win!!!", Color.WHITE, 100);
    }

    @Override
    public void onMouseLeftClick(int x, int y) {

        if (isGameStopped) {
            restart();
            return;
        }
        openTile(y, x);
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(y, x);
    }

    private void restart() {

        isGameStopped = false;
        countClosedTiles = SIDE * SIDE;
        score = 0;
        countMinesOnField = 0;
        setScore(score);
        createGame();
    }
}