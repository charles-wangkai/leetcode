class Solution {
  public int maxWeight(int[] weights, int w1, int w2) {
    boolean[][] dp = new boolean[w1 + 1][w2 + 1];
    dp[0][0] = true;

    for (int weight : weights) {
      for (int weightSum1 = w1; weightSum1 >= 0; --weightSum1) {
        for (int weightSum2 = w2; weightSum2 >= 0; --weightSum2) {
          dp[weightSum1][weightSum2] |= weightSum1 >= weight && dp[weightSum1 - weight][weightSum2];
          dp[weightSum1][weightSum2] |= weightSum2 >= weight && dp[weightSum1][weightSum2 - weight];
        }
      }
    }

    int result = 0;
    for (int weightSum1 = 0; weightSum1 <= w1; ++weightSum1) {
      for (int weightSum2 = 0; weightSum2 <= w2; ++weightSum2) {
        if (dp[weightSum1][weightSum2]) {
          result = Math.max(result, weightSum1 + weightSum2);
        }
      }
    }

    return result;
  }
}
