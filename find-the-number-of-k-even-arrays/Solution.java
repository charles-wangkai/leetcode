class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countOfArrays(int n, int m, int k) {
    int[] wayNums = {m / 2, m - m / 2};

    int[][] dp = new int[n][2];
    dp[0][0] = wayNums[0];
    dp[0][1] = wayNums[1];

    for (int i = 0; i < n - 1; ++i) {
      int[][] nextDp = new int[n][2];
      for (int curr = 0; curr < nextDp.length; ++curr) {
        for (int p = 0; p < 2; ++p) {
          if (dp[curr][p] != 0) {
            for (int q = 0; q < 2; ++q) {
              int next = curr + ((p == 0 && q == 0) ? 1 : 0);

              nextDp[next][q] = addMod(nextDp[next][q], multiplyMod(dp[curr][p], wayNums[q]));
            }
          }
        }
      }

      dp = nextDp;
    }

    return addMod(dp[k][0], dp[k][1]);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}