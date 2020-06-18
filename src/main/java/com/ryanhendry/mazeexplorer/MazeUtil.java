package com.ryanhendry.mazeexplorer;

import java.util.InputMismatchException;

public class MazeUtil {

  public static char headingToChar(Headings heading) {
    if (heading.equals(Headings.EAST)) {
      return 'E';
    } else if (heading.equals(Headings.WEST)) {
      return 'W';
    } else if (heading.equals(Headings.NORTH)) {
      return 'N';
    } else if (heading.equals(Headings.SOUTH)) {
      return 'S';
    }
    throw new InputMismatchException();
  }

}
