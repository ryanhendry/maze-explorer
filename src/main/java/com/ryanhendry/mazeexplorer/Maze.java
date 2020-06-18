package com.ryanhendry.mazeexplorer;

public class Maze {
  private final Cell[][] cells;
  private final int height;
  private final int width;
  private final int numberOfWalls;
  private final int numberOfSpaces;
  private final int startX;
  private final int startY;
  private final int endX;
  private final int endY;

  public Maze(Cell[][] cells, int height, int width, int numberOfWalls, int numberOfSpaces, int startX, int startY, int endX, int endY) {
    this.cells = cells;
    this.height = height;
    this.width = width;
    this.numberOfWalls = numberOfWalls;
    this.numberOfSpaces = numberOfSpaces;
    this.startX = startX;
    this.startY = startY;
    this.endX = endX;
    this.endY = endY;
  }

  public String prettyPrint(Explorer explorer) {
    StringBuilder sb = new StringBuilder();
    for (int y = 0; y < getHeight(); y++) {
      for (int x = 0; x < getWidth(); x++) {
        Cell cell = cells[x][y];
        if (x == explorer.getX() && y == explorer.getY()) {
          sb.append(MazeUtil.headingToChar(explorer.getHeading()));
          continue;
        }
        if (cell.isWall()) {
          sb.append("⬛");
        } else {
          sb.append("⬜");
        }
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  public Cell[][] getCells() {
    return cells;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public int getNumberOfWalls() {
    return numberOfWalls;
  }

  public int getNumberOfSpaces() {
    return numberOfSpaces;
  }

  public int getStartX() {
    return startX;
  }

  public int getStartY() {
    return startY;
  }

  public int getEndX() {
    return endX;
  }

  public int getEndY() {
    return endY;
  }
}
