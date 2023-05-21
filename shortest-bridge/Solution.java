import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int shortestBridge(int[][] grid) {
    int n = grid.length;

    int[][] distances = new int[n][n];
    for (int r = 0; r < n; ++r) {
      Arrays.fill(distances[r], -1);
    }

    Point pointOne = findPointOne(grid);
    Queue<Point> queue = new ArrayDeque<>();
    dfs(grid, distances, queue, pointOne.r(), pointOne.c());

    while (true) {
      Point head = queue.poll();

      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r() + R_OFFSETS[i];
        int adjC = head.c() + C_OFFSETS[i];
        if (adjR >= 0 && adjR < n && adjC >= 0 && adjC < n && distances[adjR][adjC] == -1) {
          distances[adjR][adjC] = distances[head.r()][head.c()] + 1;
          if (grid[adjR][adjC] == 1) {
            return distances[adjR][adjC] - 1;
          }

          queue.offer(new Point(adjR, adjC));
        }
      }
    }
  }

  Point findPointOne(int[][] grid) {
    int n = grid.length;

    for (int r = 0; ; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == 1) {
          return new Point(r, c);
        }
      }
    }
  }

  void dfs(int[][] grid, int[][] distances, Queue<Point> queue, int r, int c) {
    int n = grid.length;

    distances[r][c] = 0;
    queue.offer(new Point(r, c));

    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (adjR >= 0
          && adjR < n
          && adjC >= 0
          && adjC < n
          && grid[adjR][adjC] == 1
          && distances[adjR][adjC] == -1) {
        dfs(grid, distances, queue, adjR, adjC);
      }
    }
  }
}

record Point(int r, int c) {}
