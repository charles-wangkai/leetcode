import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public int minimumVisitedCells(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] dp = new int[m][n];
    for (int r = 0; r < dp.length; ++r) {
      Arrays.fill(dp[r], Integer.MAX_VALUE);
    }

    @SuppressWarnings("unchecked")
    PriorityQueue<Element>[] rowElements = new PriorityQueue[m];
    for (int r = 0; r < rowElements.length; ++r) {
      rowElements[r] = new PriorityQueue<>(Comparator.comparing(Element::distance));
    }

    @SuppressWarnings("unchecked")
    PriorityQueue<Element>[] colElements = new PriorityQueue[n];
    for (int c = 0; c < colElements.length; ++c) {
      colElements[c] = new PriorityQueue<>(Comparator.comparing(Element::distance));
    }

    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        if (r == 0 && c == 0) {
          dp[r][c] = 1;
        } else {
          while (!rowElements[r].isEmpty() && rowElements[r].peek().nextMaxC() < c) {
            rowElements[r].poll();
          }
          if (!rowElements[r].isEmpty()) {
            dp[r][c] = Math.min(dp[r][c], rowElements[r].peek().distance() + 1);
          }

          while (!colElements[c].isEmpty() && colElements[c].peek().nextMaxR() < r) {
            colElements[c].poll();
          }
          if (!colElements[c].isEmpty()) {
            dp[r][c] = Math.min(dp[r][c], colElements[c].peek().distance() + 1);
          }
        }

        if (dp[r][c] != Integer.MAX_VALUE) {
          rowElements[r].offer(new Element(r, c, dp[r][c], grid[r][c]));
          colElements[c].offer(new Element(r, c, dp[r][c], grid[r][c]));
        }
      }
    }

    return (dp[m - 1][n - 1] == Integer.MAX_VALUE) ? -1 : dp[m - 1][n - 1];
  }
}

record Element(int r, int c, int distance, int step) {
  int nextMaxR() {
    return r + step;
  }

  int nextMaxC() {
    return c + step;
  }
}
