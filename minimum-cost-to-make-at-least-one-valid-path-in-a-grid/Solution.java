import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
  static final int[] R_OFFSETS = {Integer.MIN_VALUE, 0, 0, 1, -1};
  static final int[] C_OFFSETS = {Integer.MIN_VALUE, 1, -1, 0, 0};

  public int minCost(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] distances = new int[m][n];
    for (int r = 0; r < m; ++r) {
      Arrays.fill(distances[r], -1);
    }

    Deque<Element> deque = new ArrayDeque<>();
    deque.offerFirst(new Element(0, 0, 0));

    while (!deque.isEmpty()) {
      Element head = deque.pollFirst();
      if (distances[head.r()][head.c()] == -1) {
        distances[head.r()][head.c()] = head.distance();

        int nextR = head.r() + R_OFFSETS[grid[head.r()][head.c()]];
        int nextC = head.c() + C_OFFSETS[grid[head.r()][head.c()]];
        if (nextR >= 0 && nextR < m && nextC >= 0 && nextC < n && distances[nextR][nextC] == -1) {
          deque.offerFirst(new Element(nextR, nextC, head.distance()));
        }

        for (int i = 1; i < R_OFFSETS.length; ++i) {
          if (i != grid[head.r()][head.c()]) {
            int adjR = head.r() + R_OFFSETS[i];
            int adjC = head.c() + C_OFFSETS[i];
            if (adjR >= 0 && adjR < m && adjC >= 0 && adjC < n && distances[adjR][adjC] == -1) {
              deque.offerLast(new Element(adjR, adjC, head.distance() + 1));
            }
          }
        }
      }
    }

    return distances[m - 1][n - 1];
  }
}

record Element(int r, int c, int distance) {}
