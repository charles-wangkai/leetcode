import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
  static final int[] R_OFFSETS = {-1, -1, 0, 1, 1, 1, 0, -1};
  static final int[] C_OFFSETS = {0, 1, 1, 1, 0, -1, -1, -1};

  public int shortestPathBinaryMatrix(int[][] grid) {
    if (grid[0][0] == 1) {
      return -1;
    }

    int n = grid.length;

    int[][] minLengths = new int[n][n];
    Queue<Point> queue = new ArrayDeque<>();
    queue.offer(new Point(0, 0));
    minLengths[0][0] = 1;

    while (!queue.isEmpty()) {
      Point head = queue.poll();
      if (head.r == n - 1 && head.c == n - 1) {
        return minLengths[head.r][head.c];
      }

      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r + R_OFFSETS[i];
        int adjC = head.c + C_OFFSETS[i];

        if (adjR >= 0
            && adjR < n
            && adjC >= 0
            && adjC < n
            && grid[adjR][adjC] == 0
            && minLengths[adjR][adjC] == 0) {
          minLengths[adjR][adjC] = minLengths[head.r][head.c] + 1;

          queue.offer(new Point(adjR, adjC));
        }
      }
    }

    return -1;
  }
}

class Point {
  int r;
  int c;

  Point(int r, int c) {
    this.r = r;
    this.c = c;
  }
}
