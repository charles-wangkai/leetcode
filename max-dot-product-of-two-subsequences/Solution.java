class Solution {
  public int maxDotProduct(int[] nums1, int[] nums2) {
    int length1 = nums1.length;
    int length2 = nums2.length;

    int[][] dp = new int[length1 + 1][length2 + 1];
    for (int i = 0; i <= length1; ++i) {
      for (int j = 0; j <= length2; ++j) {
        dp[i][j] =
            (i == 0 || j == 0)
                ? Integer.MIN_VALUE
                : Math.max(
                    Math.max(dp[i - 1][j], dp[i][j - 1]),
                    Math.max(0, dp[i - 1][j - 1]) + nums1[i - 1] * nums2[j - 1]);
      }
    }

    return dp[length1][length2];
  }
}
