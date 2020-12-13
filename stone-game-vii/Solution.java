class Solution {
  public int stoneGameVII(int[] stones) {
    int n = stones.length;

    int[][] sums = new int[n][n + 1];
    for (int i = 0; i < n; ++i) {
      int sum = 0;
      for (int j = i; j < n; ++j) {
        sum += stones[j];
        sums[i][j + 1] = sum;
      }
    }

    int[][] dp = new int[n][n];
    for (int length = 2; length <= n; ++length) {
      for (int i = 0; i + length <= n; ++i) {
        dp[i][i + length - 1] =
            Math.max(
                sums[i + 1][i + length] - dp[i + 1][i + length - 1],
                sums[i][i + length - 1] - dp[i][i + length - 2]);
      }
    }

    return dp[0][n - 1];
  }
}
