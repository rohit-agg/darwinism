package com.codegym.task.task34.task3410.view;

import com.codegym.task.task34.task3410.model.Box;
import com.codegym.task.task34.task3410.model.Player;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

  private View view;

  public Board(View view) {

    this.view = view;
  }

  public void paint(Graphics g) {

    Player player = new Player(0 ,0);
    player.draw(g);

    Box box = new Box(20, 20);
    box.draw(g);
  }
}
