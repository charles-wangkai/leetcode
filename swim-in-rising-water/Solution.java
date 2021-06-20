import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int swimInWater(int[][] grid) {
    int N = grid.length;

    int result = -1;
    int lower = grid[0][0];
    int upper = N * N - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(grid, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[][] grid, int time) {
    int N = grid.length;

    boolean[][] visited = new boolean[N][N];
    visited[0][0] = true;
    Queue<Point> queue = new ArrayDeque<>();
    queue.offer(new Point(0, 0));
    while (!queue.isEmpty()) {
      Point head = queue.poll();

      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r + R_OFFSETS[i];
        int adjC = head.c + C_OFFSETS[i];

        if (adjR >= 0
            && adjR < N
            && adjC >= 0
            && adjC < N
            && !visited[adjR][adjC]
            && grid[adjR][adjC] <= time) {
          visited[adjR][adjC] = true;
          queue.offer(new Point(adjR, adjC));
        }
      }
    }

    return visited[N - 1][N - 1];
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
