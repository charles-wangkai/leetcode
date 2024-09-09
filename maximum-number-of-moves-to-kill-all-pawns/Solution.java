import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
  static final int SIZE = 50;
  static final int[] X_OFFSETS = {-2, -2, -1, 1, 2, 2, 1, -1};
  static final int[] Y_OFFSETS = {-1, 1, 2, 2, 1, -1, -2, -2};

  public int maxMoves(int kx, int ky, int[][] positions) {
    Point[] points =
        Stream.concat(
                Stream.of(new Point(kx, ky)),
                Arrays.stream(positions).map(position -> new Point(position[0], position[1])))
            .toArray(Point[]::new);

    int[][] distances = new int[points.length][points.length];
    for (int i = 0; i < distances.length; ++i) {
      int[][] d = computeDistances(points[i].x(), points[i].y());
      for (int j = 0; j < distances.length; ++j) {
        distances[i][j] = d[points[j].x()][points[j].y()];
      }
    }

    int[][] minDp = new int[1 << positions.length][positions.length];
    for (int i = 0; i < minDp.length; ++i) {
      Arrays.fill(minDp[i], Integer.MAX_VALUE);
    }
    for (int i = 0; i < positions.length; ++i) {
      minDp[1 << i][i] = 0;
    }
    int[][] maxDp = new int[1 << positions.length][positions.length];
    for (int mask = 1; mask < 1 << positions.length; ++mask) {
      for (int index = 0; index < positions.length; ++index) {
        if (((mask >> index) & 1) == 1) {
          for (int nextIndex = 0; nextIndex < positions.length; ++nextIndex) {
            if (((mask >> nextIndex) & 1) == 0) {
              int nextMask = mask + (1 << nextIndex);
              minDp[nextMask][nextIndex] =
                  Math.min(
                      minDp[nextMask][nextIndex],
                      distances[index + 1][nextIndex + 1] + maxDp[mask][index]);
              maxDp[nextMask][nextIndex] =
                  Math.max(
                      maxDp[nextMask][nextIndex],
                      distances[index + 1][nextIndex + 1] + minDp[mask][index]);
            }
          }
        }
      }
    }

    return IntStream.range(0, positions.length)
        .map(i -> distances[0][i + 1] + minDp[(1 << positions.length) - 1][i])
        .max()
        .getAsInt();
  }

  int[][] computeDistances(int fromX, int fromY) {
    int[][] result = new int[SIZE][SIZE];
    for (int i = 0; i < result.length; ++i) {
      Arrays.fill(result[i], -1);
    }
    result[fromX][fromY] = 0;

    Queue<Point> queue = new ArrayDeque<>();
    queue.offer(new Point(fromX, fromY));

    while (!queue.isEmpty()) {
      Point head = queue.poll();
      for (int i = 0; i < X_OFFSETS.length; ++i) {
        int adjX = head.x() + X_OFFSETS[i];
        int adjY = head.y() + Y_OFFSETS[i];
        if (adjX >= 0 && adjX < SIZE && adjY >= 0 && adjY < SIZE && result[adjX][adjY] == -1) {
          result[adjX][adjY] = result[head.x()][head.y()] + 1;
          queue.offer(new Point(adjX, adjY));
        }
      }
    }

    return result;
  }
}

record Point(int x, int y) {}
