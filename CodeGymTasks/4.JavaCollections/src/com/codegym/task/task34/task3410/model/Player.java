package com.codegym.task.task34.task3410.model;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Player extends CollisionObject implements Movable {

  public Player(int x, int y) {
    super(x, y);
  }

  @Override
  public void move(int x, int y) {

    this.setX(this.getX() + x);
    this.setY(this.getY() + y);
  }

  @Override
  public void draw(Graphics graphics) {

    int width = this.getWidth(),
        height = this.getHeight();

    Graphics2D graphics2d = (Graphics2D) graphics;
    Ellipse2D.Double circle = new Ellipse2D.Double(this.getX() - (width / 2), this.getY() - (height / 2), this.getHeight(), this.getWidth());

    graphics2d.setColor(Color.YELLOW);
    graphics2d.fill(circle);
  }
}
