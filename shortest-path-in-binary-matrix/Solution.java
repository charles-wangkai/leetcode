import java.util.LinkedList;
import java.util.Queue;

class Solution {
  static final int[] R_OFFSETS = {-1, -1, 0, 1, 1, 1, 0, -1};
  static final int[] C_OFFSETS = {0, 1, 1, 1, 0, -1, -1, -1};

  public int shortestPathBinaryMatrix(int[][] grid) {
    if (grid[0][0] == 1) {
      return -1;
    }

    int N = grid.length;

    int[][] minLengths = new int[N][N];
    Queue<Point> queue = new LinkedList<>();
    queue.offer(new Point(0, 0));
    minLengths[0][0] = 1;

    while (!queue.isEmpty()) {
      Point head = queue.poll();
      if (head.r == N - 1 && head.c == N - 1) {
        return minLengths[head.r][head.c];
      }

      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int nextR = head.r + R_OFFSETS[i];
        int nextC = head.c + C_OFFSETS[i];

        if (nextR >= 0
            && nextR < N
            && nextC >= 0
            && nextC < N
            && grid[nextR][nextC] == 0
            && minLengths[nextR][nextC] == 0) {
          minLengths[nextR][nextC] = minLengths[head.r][head.c] + 1;

          queue.offer(new Point(nextR, nextC));
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
