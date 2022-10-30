import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int shortestPath(int[][] grid, int k) {
    int m = grid.length;
    int n = grid[0].length;

    int[][][] minDistances = new int[m][n][k + 1];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        Arrays.fill(minDistances[r][c], Integer.MAX_VALUE);
      }
    }
    minDistances[0][0][k] = 0;

    Queue<Element> queue = new ArrayDeque<>();
    queue.offer(new Element(0, 0, k));

    while (!queue.isEmpty()) {
      Element head = queue.poll();

      for (int i = 0; i < R_OFFSETS.length; ++i) {
        int adjR = head.r() + R_OFFSETS[i];
        int adjC = head.c() + C_OFFSETS[i];
        if (adjR >= 0 && adjR < m && adjC >= 0 && adjC < n) {
          if (grid[adjR][adjC] == 0) {
            if (minDistances[adjR][adjC][head.elimRest()] == Integer.MAX_VALUE) {
              minDistances[adjR][adjC][head.elimRest()] =
                  minDistances[head.r()][head.c()][head.elimRest()] + 1;
              queue.offer(new Element(adjR, adjC, head.elimRest()));
            }
          } else {
            if (head.elimRest() != 0
                && minDistances[adjR][adjC][head.elimRest() - 1] == Integer.MAX_VALUE) {
              minDistances[adjR][adjC][head.elimRest() - 1] =
                  minDistances[head.r()][head.c()][head.elimRest()] + 1;
              queue.offer(new Element(adjR, adjC, head.elimRest() - 1));
            }
          }
        }
      }
    }

    int result = Arrays.stream(minDistances[m - 1][n - 1]).min().getAsInt();

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }
}

record Element(int r, int c, int elimRest) {}
