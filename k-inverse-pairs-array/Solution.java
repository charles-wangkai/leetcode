class Solution {
  static final int MODULUS = 1_000_000_007;

  public int kInversePairs(int n, int k) {
    int[][] dp = new int[n + 1][k + 1];
    dp[1][0] = 1;
    for (int i = 2; i <= n; ++i) {
      int sum = 0;
      for (int j = 0; j <= k; ++j) {
        sum = addMod(sum, dp[i - 1][j]);

        if (j >= i) {
          sum = addMod(sum, -dp[i - 1][j - i]);
        }

        dp[i][j] = sum;
      }
    }

    return dp[n][k];
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}
