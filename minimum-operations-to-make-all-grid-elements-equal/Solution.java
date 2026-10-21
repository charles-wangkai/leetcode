// https://chatgpt.com/share/6ad81467-3d04-83ec-b1a0-1068da184610

class Solution {
  public long minOperations(int[][] grid, int k) {
    int m = grid.length;
    int n = grid[0].length;

    long lower = Long.MIN_VALUE;
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        lower = Math.max(lower, grid[r][c]);
      }
    }

    Long fixedTarget = null;

    long[][] da = new long[m + 1][n + 1];
    long[][] db = new long[m + 1][n + 1];

    long totalA = 0;
    long totalB = 0;

    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        da[r][c] +=
            ((r == 0) ? 0 : da[r - 1][c])
                + ((c == 0) ? 0 : da[r][c - 1])
                - ((r == 0 || c == 0) ? 0 : da[r - 1][c - 1]);
        db[r][c] +=
            ((r == 0) ? 0 : db[r - 1][c])
                + ((c == 0) ? 0 : db[r][c - 1])
                - ((r == 0 || c == 0) ? 0 : db[r - 1][c - 1]);

        long needA = 1 - da[r][c];
        long needB = -grid[r][c] - db[r][c];

        if (r + k <= m && c + k <= n) {
          if (needA == 1) {
            lower = Math.max(lower, -needB);
          } else if (needB < 0) {
            return -1;
          }

          totalA += needA;
          totalB += needB;

          da[r][c] += needA;
          db[r][c] += needB;

          da[r + k][c] -= needA;
          db[r + k][c] -= needB;

          da[r][c + k] -= needA;
          db[r][c + k] -= needB;

          da[r + k][c + k] += needA;
          db[r + k][c + k] += needB;
        } else if (needA == 1) {
          long t = -needB;

          if (fixedTarget == null) {
            fixedTarget = t;
          } else if (fixedTarget != t) {
            return -1;
          }
        } else if (needB != 0) {
          return -1;
        }
      }
    }

    long target;
    if (fixedTarget == null) {
      target = lower;
    } else if (fixedTarget < lower) {
      return -1;
    } else {
      target = fixedTarget;
    }

    return totalA * target + totalB;
  }
}
