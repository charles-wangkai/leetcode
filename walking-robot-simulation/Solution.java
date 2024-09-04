import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  static final int[] X_OFFSETS = {0, 1, 0, -1};
  static final int[] Y_OFFSETS = {1, 0, -1, 0};

  public int robotSim(int[] commands, int[][] obstacles) {
    Set<Point> obstacleSet =
        Arrays.stream(obstacles)
            .map(obstacle -> new Point(obstacle[0], obstacle[1]))
            .collect(Collectors.toSet());

    int result = 0;
    int x = 0;
    int y = 0;
    int direction = 0;
    for (int command : commands) {
      if (command == -2) {
        direction = Math.floorMod(direction - 1, X_OFFSETS.length);
      } else if (command == -1) {
        direction = (direction + 1) % X_OFFSETS.length;
      } else {
        for (int i = 0; i < command; ++i) {
          int nextX = x + X_OFFSETS[direction];
          int nextY = y + Y_OFFSETS[direction];
          if (obstacleSet.contains(new Point(nextX, nextY))) {
            break;
          }

          x = nextX;
          y = nextY;
        }

        result = Math.max(result, x * x + y * y);
      }
    }

    return result;
  }
}

record Point(int x, int y) {}
