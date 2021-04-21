import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int getFood(char[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    Point start = findStart(grid);
    Queue<Point> queue = new ArrayDeque<>();
    queue.offer(start);
    int[][] distances = new int[m][n];
    for (int r = 0; r < m; ++r) {
      Arrays.fill(distances[r], -1);
    }
    distances[start.r][start.c] = 0;
    while (!queue.isEmpty()) {
      Point head = queue.poll();
      if (grid[head.r][head.c] == '#') {
        return distances[head.r][head.c];
      }

      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r + R_OFFSETS[i];
        int adjC = head.c + C_OFFSETS[i];
        if (adjR >= 0
            && adjR < m
            && adjC >= 0
            && adjC < n
            && grid[adjR][adjC] != 'X'
            && distances[adjR][adjC] == -1) {
          distances[adjR][adjC] = distances[head.r][head.c] + 1;
          queue.offer(new Point(adjR, adjC));
        }
      }
    }

    return -1;
  }

  Point findStart(char[][] grid) {
    for (int r = 0; ; ++r) {
      for (int c = 0; c < grid[r].length; ++c) {
        if (grid[r][c] == '*') {
          return new Point(r, c);
        }
      }
    }
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
