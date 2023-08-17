import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int[][] updateMatrix(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;

    int[][] result = new int[m][n];
    for (int r = 0; r < result.length; ++r) {
      Arrays.fill(result[r], -1);
    }

    Queue<Point> queue = new ArrayDeque<>();
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (mat[r][c] == 0) {
          result[r][c] = 0;
          queue.offer(new Point(r, c));
        }
      }
    }

    while (!queue.isEmpty()) {
      Point head = queue.poll();

      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r() + R_OFFSETS[i];
        int adjC = head.c() + C_OFFSETS[i];
        if (adjR >= 0 && adjR < m && adjC >= 0 && adjC < n && result[adjR][adjC] == -1) {
          result[adjR][adjC] = result[head.r()][head.c()] + 1;
          queue.offer(new Point(adjR, adjC));
        }
      }
    }

    return result;
  }
}

record Point(int r, int c) {}
