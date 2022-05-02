import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int maximumMinutes(int[][] grid) {
    int[][] fireDistances = buildFireDistances(grid);

    int result = -1;
    int lower = 0;
    int upper = 1_000_000_000;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(grid, fireDistances, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  int[][] buildFireDistances(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] result = new int[m][n];
    for (int r = 0; r < result.length; ++r) {
      Arrays.fill(result[r], Integer.MAX_VALUE);
    }
    Queue<Point> queue = new ArrayDeque<>();
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (grid[r][c] == 1) {
          result[r][c] = 0;
          queue.offer(new Point(r, c));
        }
      }
    }
    while (!queue.isEmpty()) {
      Point head = queue.poll();
      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r + R_OFFSETS[i];
        int adjC = head.c + C_OFFSETS[i];
        if (adjR >= 0
            && adjR < m
            && adjC >= 0
            && adjC < n
            && grid[adjR][adjC] != 2
            && result[adjR][adjC] == Integer.MAX_VALUE) {
          result[adjR][adjC] = result[head.r][head.c] + 1;
          queue.offer(new Point(adjR, adjC));
        }
      }
    }

    return result;
  }

  boolean check(int[][] grid, int[][] fireDistances, int stayTime) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] escapeDistances = new int[m][n];
    for (int r = 0; r < escapeDistances.length; ++r) {
      Arrays.fill(escapeDistances[r], -1);
    }
    escapeDistances[0][0] = stayTime;
    Queue<Point> queue = new ArrayDeque<>();
    queue.offer(new Point(0, 0));
    while (!queue.isEmpty()) {
      Point head = queue.poll();
      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r + R_OFFSETS[i];
        int adjC = head.c + C_OFFSETS[i];
        if (adjR >= 0
            && adjR < m
            && adjC >= 0
            && adjC < n
            && grid[adjR][adjC] != 2
            && escapeDistances[adjR][adjC] == -1) {
          escapeDistances[adjR][adjC] = escapeDistances[head.r][head.c] + 1;
          if (adjR == m - 1 && adjC == n - 1) {
            return escapeDistances[adjR][adjC] <= fireDistances[adjR][adjC];
          }

          if (escapeDistances[adjR][adjC] < fireDistances[adjR][adjC]) {
            queue.offer(new Point(adjR, adjC));
          }
        }
      }
    }

    return false;
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