import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
  public int shortestPathBinaryMatrix(int[][] grid) {
    if (grid[0][0] == 1) {
      return -1;
    }

    int n = grid.length;

    int[][] minLengths = new int[n][n];
    for (int r = 0; r < minLengths.length; ++r) {
      Arrays.fill(minLengths[r], -1);
    }
    minLengths[0][0] = 1;

    Queue<Point> queue = new ArrayDeque<>();
    queue.offer(new Point(0, 0));

    while (!queue.isEmpty()) {
      Point head = queue.poll();

      for (int dr = -1; dr <= 1; ++dr) {
        for (int dc = -1; dc <= 1; ++dc) {
          int adjR = head.r() + dr;
          int adjC = head.c() + dc;
          if (adjR >= 0
              && adjR < n
              && adjC >= 0
              && adjC < n
              && grid[adjR][adjC] == 0
              && minLengths[adjR][adjC] == -1) {
            minLengths[adjR][adjC] = minLengths[head.r()][head.c()] + 1;
            queue.offer(new Point(adjR, adjC));
          }
        }
      }
    }

    return minLengths[n - 1][n - 1];
  }
}

record Point(int r, int c) {}
