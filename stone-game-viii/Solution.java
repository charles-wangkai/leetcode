class Solution {
  public int stoneGameVIII(int[] stones) {
    int n = stones.length;

    int[] prefixSums = new int[n];
    for (int i = 0; i < prefixSums.length; ++i) {
      prefixSums[i] = ((i == 0) ? 0 : prefixSums[i - 1]) + stones[i];
    }

    int[] dp = new int[n - 1];
    dp[n - 2] = prefixSums[n - 1];
    int maxDiff = Math.max(dp[n - 2], prefixSums[n - 2] - dp[n - 2]);
    for (int i = n - 3; i >= 0; --i) {
      dp[i] = maxDiff;
      maxDiff = Math.max(maxDiff, prefixSums[i] - dp[i]);
    }

    return dp[0];
  }
}
