import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int minimumObstacles(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] distances = new int[m][n];
    for (int r = 0; r < distances.length; ++r) {
      Arrays.fill(distances[r], -1);
    }
    distances[0][0] = 0;

    Deque<Point> deque = new ArrayDeque<>();
    deque.offerFirst(new Point(0, 0));

    while (!deque.isEmpty()) {
      Point head = deque.pollFirst();
      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r() + R_OFFSETS[i];
        int adjC = head.c() + C_OFFSETS[i];
        if (adjR >= 0 && adjR < m && adjC >= 0 && adjC < n && distances[adjR][adjC] == -1) {
          distances[adjR][adjC] = distances[head.r()][head.c()] + grid[adjR][adjC];
          if (grid[adjR][adjC] == 0) {
            deque.offerFirst(new Point(adjR, adjC));
          } else {
            deque.offerLast(new Point(adjR, adjC));
          }
        }
      }
    }

    return distances[m - 1][n - 1];
  }
}

record Point(int r, int c) {}
