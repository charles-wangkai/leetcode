import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int maxDistance(int[][] grid) {
    int n = grid.length;

    int[][] distances = new int[n][n];
    Queue<Point> queue = new ArrayDeque<>();
    for (int r = 0; r < n; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == 1) {
          queue.offer(new Point(r, c));
        }
      }
    }

    int result = -1;
    while (!queue.isEmpty()) {
      Point head = queue.poll();

      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r() + R_OFFSETS[i];
        int adjC = head.c() + C_OFFSETS[i];
        if (adjR >= 0
            && adjR < n
            && adjC >= 0
            && adjC < n
            && grid[adjR][adjC] == 0
            && distances[adjR][adjC] == 0) {
          distances[adjR][adjC] = distances[head.r()][head.c()] + 1;
          result = distances[adjR][adjC];

          queue.offer(new Point(adjR, adjC));
        }
      }
    }

    return result;
  }
}

record Point(int r, int c) {}
