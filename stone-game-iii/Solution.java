import java.util.Arrays;

class Solution {
  public String stoneGameIII(int[] stoneValue) {
    int[] dp = new int[stoneValue.length + 1];
    Arrays.fill(dp, Integer.MIN_VALUE);
    dp[dp.length - 1] = 0;

    for (int i = dp.length - 2; i >= 0; --i) {
      int taken = stoneValue[i];
      for (int j = 1; j <= 3 && i + j < dp.length; ++j) {
        dp[i] = Math.max(dp[i], taken - dp[i + j]);

        if (i + j != dp.length - 1) {
          taken += stoneValue[i + j];
        }
      }
    }

    if (dp[0] > 0) {
      return "Alice";
    }
    if (dp[0] < 0) {
      return "Bob";
    }

    return "Tie";
  }
}
