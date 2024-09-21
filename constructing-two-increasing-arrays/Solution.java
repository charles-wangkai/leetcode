import java.util.Arrays;

class Solution {
  public int minLargest(int[] nums1, int[] nums2) {
    int[][] dp = new int[nums1.length + 1][nums2.length + 1];
    for (int i = 0; i < dp.length; ++i) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }
    dp[0][0] = 0;

    for (int i = 0; i <= nums1.length; ++i) {
      for (int j = 0; j <= nums2.length; ++j) {
        if (i != 0) {
          dp[i][j] = Math.min(dp[i][j], findNext(dp[i - 1][j], nums1[i - 1]));
        }
        if (j != 0) {
          dp[i][j] = Math.min(dp[i][j], findNext(dp[i][j - 1], nums2[j - 1]));
        }
      }
    }

    return dp[nums1.length][nums2.length];
  }

  int findNext(int last, int value) {
    int result = last + 1;
    while (result % 2 != value % 2) {
      ++result;
    }

    return result;
  }
}