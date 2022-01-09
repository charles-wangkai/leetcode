class Solution {
  static final int[] X_OFFSETS = {0, 1, 0, -1};
  static final int[] Y_OFFSETS = {1, 0, -1, 0};

  public boolean isRobotBounded(String instructions) {
    int x = 0;
    int y = 0;
    int direction = 0;
    for (char instruction : instructions.toCharArray()) {
      if (instruction == 'L') {
        direction = (direction - 1 + X_OFFSETS.length) % X_OFFSETS.length;
      } else if (instruction == 'R') {
        direction = (direction + 1) % X_OFFSETS.length;
      } else {
        x += X_OFFSETS[direction];
        y += Y_OFFSETS[direction];
      }
    }

    return direction != 0 || (x == 0 && y == 0);
  }
}
