class Solution {
  public int maxSumAfterPartitioning(int[] arr, int k) {
    int[] dp = new int[arr.length];
    for (int i = 0; i < dp.length; ++i) {
      int max = -1;
      for (int j = i; j >= 0 && j > i - k; --j) {
        max = Math.max(max, arr[j]);
        dp[i] = Math.max(dp[i], ((j == 0) ? 0 : dp[j - 1]) + max * (i - j + 1));
      }
    }

    return dp[dp.length - 1];
  }
}
