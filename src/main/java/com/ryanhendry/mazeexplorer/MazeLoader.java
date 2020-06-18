package com.ryanhendry.mazeexplorer;

import java.io.InputStream;
import java.util.Scanner;

public class MazeLoader {

  public Maze load(String mazePath) {
    int mazeHeight = getHeight(mazePath);
    int mazeWidth = getWidth(mazePath);
    InputStream is = MazeLoader.class.getResourceAsStream(mazePath);
    Scanner sc = new Scanner(is);
    Cell[][] cells = new Cell[mazeWidth][mazeHeight];
    int y = 0;
    int numberOfWalls = 0;
    int numberOfSpaces = 0;
    int startX = 0;
    int startY = 0;
    int endX = 0;
    int endY = 0;
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      char[] chars =  line.toCharArray();
      for (int x = 0; x < chars.length; x++) {
        switch (chars[x]) {
          case 'X':
            numberOfWalls++;
            break;
          case ' ':
            numberOfSpaces++;
            break;
          case 'S':
            startX = x;
            startY = y;
            break;
          case 'F':
            endX = x;
            endY = y;
            break;
        }
        cells[x][y] = new Cell(chars[x] == 'X');
      }
      y++;
    }
    sc.close();
    return new Maze(cells, mazeHeight, mazeWidth, numberOfWalls, numberOfSpaces, startX, startY, endX, endY);
  }

  private int getHeight(String mazePath) {
    InputStream is = MazeLoader.class.getResourceAsStream(mazePath);
    Scanner sc = new Scanner(is);
    int height = 0;
    while (sc.hasNextLine()) {
      height++;
      sc.nextLine();
    }
    sc.close();
    return height;
  }

  private int getWidth(String mazePath) {
    InputStream is = MazeLoader.class.getResourceAsStream(mazePath);
    Scanner sc = new Scanner(is);
    if (!sc.hasNextLine()) {
      return 0;
    }
    String line = sc.nextLine();
    int length = line.length();
    sc.close();
    return length;
  }
}
