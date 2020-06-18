package com.ryanhendry.mazeexplorer;

public class Cell {
  private final boolean isWall;


  public Cell(boolean isWall) {
    this.isWall = isWall;
  }

  public boolean isWall() {
    return isWall;
  }
}
