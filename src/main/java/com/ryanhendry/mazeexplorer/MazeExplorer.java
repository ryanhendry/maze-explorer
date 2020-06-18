package com.ryanhendry.mazeexplorer;

import static com.ryanhendry.mazeexplorer.Headings.EAST;
import static com.ryanhendry.mazeexplorer.Headings.NORTH;
import static com.ryanhendry.mazeexplorer.Headings.SOUTH;
import static com.ryanhendry.mazeexplorer.Headings.WEST;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MazeExplorer {

  private final Maze maze;
  private final Explorer explorer;
  private final Scanner inScanner;
  private boolean exit = false;

  public static void main (String... args) {
    MazeLoader mazeLoader = new MazeLoader();
    Maze maze = mazeLoader.load("/Maze1.txt");
    Explorer explorer = new Explorer(maze.getStartX(), maze.getStartY(), NORTH);
    MazeExplorer mazeExplorer = new MazeExplorer(maze, explorer, new Scanner(System.in));
    mazeExplorer.run();
  }

  public MazeExplorer(Maze maze, Explorer explorer, Scanner inScanner) {
    this.maze = maze;
    this.explorer = explorer;
    this.inScanner = inScanner;
  }

  public void run() {
    while (!exit) {
      System.out.println("Options:\n exit - exits the game \n info x,y - query a maze cell \n info maze - returns info about the maze \n explore - explore the maze");
      System.out.print("Command: ");
      String command = inScanner.nextLine();
      if (command.equals("info maze")) {
        printMazeInfo(maze, explorer);
      } else if (command.matches("info [0-9]+,[0-9]+")) {
        printCellInfo(maze, command);
      } else if (command.matches("explore")) {
        exploreMaze(maze, explorer, inScanner);
      } else if (command.equals("exit")) {
        exit = true;
        System.out.println("Goodbye explorer!");
      } else {
        System.out.println("Unknown command");
      }
    }
  }

  private void exploreMaze(Maze maze, Explorer explorer, Scanner scan) {
    List<Pair<Integer, Integer>> movementHistory = new ArrayList<>();
    while (true) {
      System.out.println(maze.prettyPrint(explorer));
      if (explorer.getX() == maze.getEndX() && explorer.getY() == maze.getEndY()) {
        System.out.println("Congratulations you solved the maze!");
        exit = true;
        return;
      }
      Cell westCell = maze.getCells()[explorer.getX() - 1][explorer.getY()];
      Cell eastCell = maze.getCells()[explorer.getX() + 1][explorer.getY()];
      Cell northCell = maze.getCells()[explorer.getX()][explorer.getY() - 1];
      Cell southCell = maze.getCells()[explorer.getX()][explorer.getY() + 1];
      System.out.println(String.format("You are facing: %s", explorer.getHeading()));
      printFacing(explorer, westCell, eastCell, northCell, southCell);
      System.out.println("Available commands (you must face the direction before moving):");
      printAvailableCommands(westCell, eastCell, northCell, southCell);
      System.out.print("Command: ");
      String cmd = scan.nextLine().toUpperCase();
      switch (cmd) {
        case "E":
          if (explorer.getHeading() != EAST) {
            explorer.setHeading(EAST);
          } else if (!eastCell.isWall()) {
            movementHistory.add(new Pair<>(explorer.getX(), explorer.getY()));
            explorer.setX(explorer.getX() + 1);
          }
          break;
        case "W":
          if (explorer.getHeading() != WEST) {
            explorer.setHeading(WEST);
          } else if (!westCell.isWall()) {
            movementHistory.add(new Pair<>(explorer.getX(), explorer.getY()));
            explorer.setX(explorer.getX() - 1);
          }
          break;
        case "S":
          if (explorer.getHeading() != SOUTH) {
            explorer.setHeading(SOUTH);
          } else if (!southCell.isWall()) {
            movementHistory.add(new Pair<>(explorer.getX(), explorer.getY()));
            explorer.setY(explorer.getY() + 1);
          }
          break;
        case "N":
          if (explorer.getHeading() != NORTH) {
            explorer.setHeading(NORTH);
          } else if (!northCell.isWall()) {
            movementHistory.add(new Pair<>(explorer.getX(), explorer.getY()));
            explorer.setY(explorer.getY() - 1);
          }
          break;
        case "H":
          StringBuilder stringBuilder = new StringBuilder();
          for (Pair<Integer, Integer> p: movementHistory) {
            stringBuilder.append(p);
            stringBuilder.append(" ");
          }
          System.out.println("You have been to: " + stringBuilder.toString());
          break;
        case "Q":
          return;
        default:
          System.out.println("Unknown command");
          break;
      }
    }
  }

  private void printFacing(Explorer explorer, Cell westCell, Cell eastCell, Cell northCell, Cell southCell) {
    Cell facingCell;
    if (explorer.getHeading() == NORTH) {
      facingCell = northCell;
    } else if (explorer.getHeading() == SOUTH) {
      facingCell = southCell;
    } else if (explorer.getHeading() == EAST) {
      facingCell = eastCell;
    } else {
      facingCell = westCell;
    }
    System.out.println(String.format("To the %s lies %s", explorer.getHeading(), facingCell.isWall() ? "a wall" : "empty space"));
  }

  private void printAvailableCommands(Cell westCell, Cell eastCell, Cell northCell, Cell southCell) {
    if (!westCell.isWall()) {
      System.out.println("w - face/go west");
    }
    if (!eastCell.isWall()) {
      System.out.println("e - face/go east");
    }
    if (!northCell.isWall()) {
      System.out.println("n - face/go north");
    }
    if (!southCell.isWall()) {
      System.out.println("s - face/go south");
    }
    System.out.println("h - show previous locations");
    System.out.println("q - main menu");
  }

  private void printCellInfo(Maze maze, String command) {
    String[] split = command.split(" ");
    String[] coords = split[1].split(",");
    int x = Integer.parseInt(coords[0]);
    int y = Integer.parseInt(coords[1]);
    if (x >= maze.getCells().length || y >= maze.getCells()[x].length || x < 0 || y < 0) {
      System.out.println("Cell not found");
      return;
    }
    Cell cell = maze.getCells()[x][y];
    if (cell.isWall()) {
      System.out.println(String.format("Cell %s,%s is a wall", coords[0], coords[1]));
    } else  {
      System.out.println(String.format("Cell %s,%s is empty space", coords[0], coords[1]));
    }
  }

  private void printMazeInfo(Maze maze, Explorer explorer) {
    System.out.println(maze.prettyPrint(explorer));
    System.out.println(String.format("Walls: %d Empty spaces: %d \n", maze.getNumberOfWalls(), maze.getNumberOfSpaces()));
  }
}
