class Robot {
  static final String[] DIRECTIONS = {"North", "East", "South", "West"};
  static final int[] X_OFFSETS = {0, 1, 0, -1};
  static final int[] Y_OFFSETS = {1, 0, -1, 0};

  int width;
  int height;
  int x;
  int y;
  int directionIndex = 1;

  public Robot(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public void move(int num) {
    num %= (width + height - 2) * 2;

    for (int i = 0; i < num; ++i) {
      int nextX = x + X_OFFSETS[directionIndex];
      int nextY = y + Y_OFFSETS[directionIndex];
      if (nextX >= 0 && nextX < width && nextY >= 0 && nextY < height) {
        x = nextX;
        y = nextY;
      } else {
        directionIndex = (directionIndex - 1 + DIRECTIONS.length) % DIRECTIONS.length;
        x += X_OFFSETS[directionIndex];
        y += Y_OFFSETS[directionIndex];
      }
    }

    if (x == 0 && y == 0) {
      directionIndex = 2;
    }
  }

  public int[] getPos() {
    return new int[] {x, y};
  }

  public String getDir() {
    return DIRECTIONS[directionIndex];
  }
}

// Your Robot object will be instantiated and called as such:
// Robot obj = new Robot(width, height);
// obj.move(num);
// int[] param_2 = obj.getPos();
// String param_3 = obj.getDir();
