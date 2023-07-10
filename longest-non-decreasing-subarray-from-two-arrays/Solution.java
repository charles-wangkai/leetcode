import java.util.Arrays;

class Solution {
  public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
    int[][] values = {nums1, nums2};

    int[][] dp = new int[nums1.length][2];
    for (int i = 0; i < dp.length; ++i) {
      for (int j = 0; j <= 1; ++j) {
        dp[i][j] = 1;

        if (i != 0) {
          for (int k = 0; k <= 1; ++k) {
            if (values[j][i] >= values[k][i - 1]) {
              dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + 1);
            }
          }
        }
      }
    }

    return Arrays.stream(dp).flatMapToInt(Arrays::stream).max().getAsInt();
  }
}
