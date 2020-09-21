package com.codegym.task.task34.task3410.model;

public abstract class CollisionObject extends GameObject {

  public CollisionObject(int x, int y) {
    super(x, y);
  }

  public boolean isCollision(GameObject gameObject, Direction direction) {

    int x = this.getX(), y = this.getY();
    switch (direction) {
      case LEFT:
        x -= BOARD_CELL_SIZE;
        break;
      case RIGHT:
        x += BOARD_CELL_SIZE;
        break;
      case UP:
        y -= BOARD_CELL_SIZE;
        break;
      case DOWN:
        y += BOARD_CELL_SIZE;
        break;
    }

    return (x == gameObject.getX() && y == gameObject.getY());
  }
}
