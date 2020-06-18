package com.ryanhendry.mazeexplorer;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static com.ryanhendry.mazeexplorer.Headings.EAST;
import static com.ryanhendry.mazeexplorer.Headings.NORTH;
import static com.ryanhendry.mazeexplorer.Headings.WEST;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class MazeExplorerTest {

  @Test
  void testExitGame() {
    Maze maze = getTestMaze();
    Explorer explorer = new Explorer(maze.getStartX(), maze.getStartY(), NORTH);
    String input = "exit\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    Scanner scanner = new Scanner(System.in);
    MazeExplorer mazeExplorer = new MazeExplorer(maze, explorer, scanner);
    mazeExplorer.run();
  }

  @Test
  void testChangeExplorerFacing() {
    Maze maze = getTestMaze();
    Explorer explorer = new Explorer(maze.getStartX(), maze.getStartY(), NORTH);
    String input = "explore\ne\nq\nexit\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    Scanner scanner = new Scanner(System.in);
    MazeExplorer mazeExplorer = new MazeExplorer(maze, explorer, scanner);
    mazeExplorer.run();
    assertEquals(EAST, explorer.getHeading());
  }

  @Test
  void testMoveExplorerToEmptySpace() {
    Maze maze = getTestMaze();
    Explorer explorer = new Explorer(maze.getStartX(), maze.getStartY(), NORTH);
    String input = "explore\ne\ne\nq\nexit\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    Scanner scanner = new Scanner(System.in);
    MazeExplorer mazeExplorer = new MazeExplorer(maze, explorer, scanner);
    mazeExplorer.run();
    assertEquals(EAST, explorer.getHeading());
    assertEquals(2, explorer.getX());
    assertEquals(1, explorer.getY());
  }

  @Test
  void testMoveExplorerToWallDoesntMove() {
    Maze maze = getTestMaze();
    Explorer explorer = new Explorer(maze.getStartX(), maze.getStartY(), NORTH);
    String input = "explore\nw\nw\nq\nexit\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    Scanner scanner = new Scanner(System.in);
    MazeExplorer mazeExplorer = new MazeExplorer(maze, explorer, scanner);
    mazeExplorer.run();
    assertEquals(WEST, explorer.getHeading());
    assertEquals(1, explorer.getX());
    assertEquals(1, explorer.getY());
  }

  @Test
  void testMoveToFinalSquareWins() {
    Maze maze = getTestMaze();
    Explorer explorer = new Explorer(maze.getStartX(), maze.getStartY(), NORTH);
    String input = "explore\ne\ne\ne";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    Scanner scanner = new Scanner(System.in);
    MazeExplorer mazeExplorer = new MazeExplorer(maze, explorer, scanner);
    mazeExplorer.run();
  }

  private Maze getTestMaze() {
    Cell[][] cells = new Cell[4][3];
    cells[0][0] = new Cell(true);
    cells[0][1] = new Cell(true);
    cells[0][2] = new Cell(true);
    cells[1][0] = new Cell(true);
    cells[1][1] = new Cell(false);
    cells[1][2] = new Cell(true);
    cells[2][0] = new Cell(true);
    cells[2][1] = new Cell(false);
    cells[2][2] = new Cell(true);
    cells[3][0] = new Cell(true);
    cells[3][1] = new Cell(false);
    cells[3][2] = new Cell(true);
    return new Maze(cells,3,4,5,3,1,1,3,1);
  }
}
