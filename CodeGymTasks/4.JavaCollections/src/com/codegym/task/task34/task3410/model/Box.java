package com.codegym.task.task34.task3410.model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Box extends CollisionObject implements Movable {

  public Box(int x, int y) {
    super(x, y);
  }

  @Override
  public void draw(Graphics graphics) {

    int width = this.getWidth(),
        height = this.getHeight();

    Graphics2D graphics2d = (Graphics2D) graphics;
    Rectangle2D.Double rectangle = new Rectangle2D.Double(this.getX() - (width / 2), this.getY() - (height / 2), width, height);

    graphics2d.setColor(Color.ORANGE);
    graphics2d.fill(rectangle);
    graphics2d.setColor(Color.BLACK);
    graphics2d.drawLine(this.getX() - (width / 2), this.getY() - (height / 2), this.getX() + (width / 2), this.getY() + (height / 2));
    graphics2d.drawLine(this.getX() + (width / 2), this.getY() - (height / 2), this.getX() - (width / 2), this.getY() + (height / 2));
  }

  @Override
  public void move(int x, int y) {

    this.setX(this.getX() + x);
    this.setY(this.getY() + y);
  }
}
