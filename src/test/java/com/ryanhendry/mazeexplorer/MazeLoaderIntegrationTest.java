package com.ryanhendry.mazeexplorer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MazeLoaderIntegrationTest {

  @Test
  void testLoadMaze() {
    MazeLoader mazeLoader = new MazeLoader();
    Maze maze = mazeLoader.load("/test-maze.txt");
    assertEquals(1, maze.getStartX());
    assertEquals(1, maze.getStartY());
    assertEquals(3, maze.getEndX());
    assertEquals(1, maze.getEndY());
    assertEquals(4, maze.getWidth());
    assertEquals(3, maze.getHeight());
    assertTrue(maze.getCells()[0][0].isWall());
    assertFalse(maze.getCells()[1][1].isWall());
  }
}