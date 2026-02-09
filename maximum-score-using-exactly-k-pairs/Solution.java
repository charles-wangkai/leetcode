class Solution {
  public long maxScore(int[] nums1, int[] nums2, int k) {
    int n = nums1.length;
    int m = nums2.length;

    long[][][] dp = new long[n + 1][m + 1][k + 1];
    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j <= m; ++j) {
        for (int p = 0; p <= k; ++p) {
          if (i == 0 || j == 0) {
            dp[i][j][p] = (p == 0) ? 0 : Long.MIN_VALUE;
          } else {
            dp[i][j][p] = Math.max(dp[i - 1][j][p], dp[i][j - 1][p]);

            if (p != 0 && dp[i - 1][j - 1][p - 1] != Long.MIN_VALUE) {
              dp[i][j][p] =
                  Math.max(
                      dp[i][j][p], dp[i - 1][j - 1][p - 1] + (long) nums1[i - 1] * nums2[j - 1]);
            }
          }
        }
      }
    }

    return dp[n][m][k];
  }
}