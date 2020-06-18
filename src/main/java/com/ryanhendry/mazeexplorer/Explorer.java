package com.ryanhendry.mazeexplorer;

public class Explorer {
  private int x;
  private int y;
  private Headings heading;

  public Explorer(int x, int y, Headings heading) {
    this.x = x;
    this.y = y;
    this.heading = heading;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Headings getHeading() {
    return heading;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setHeading(Headings heading) {
    this.heading = heading;
  }
}
