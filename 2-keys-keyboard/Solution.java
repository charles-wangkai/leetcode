import java.util.Arrays;

class Solution {
  public int minSteps(int n) {
    int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[1] = 0;
    for (int i = 1; i < dp.length; ++i) {
      int operationNum = dp[i] + 1;
      for (int j = i + i; j < dp.length; j += i) {
        ++operationNum;
        dp[j] = Math.min(dp[j], operationNum);
      }
    }

    return dp[n];
  }
}
