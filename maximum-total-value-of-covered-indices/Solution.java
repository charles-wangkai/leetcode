class Solution {
  public long maxTotal(int[] nums, String s) {
    long[] dp = new long[2];
    if (s.charAt(0) == '1') {
      dp[1] = nums[0];
    }

    for (int i = 1; i < nums.length; ++i) {
      long[] nextDp = new long[2];
      if (s.charAt(i) == '0') {
        nextDp[0] = Math.max(dp[0], dp[1]);
      } else {
        nextDp[0] = dp[0] + nums[i - 1];
        nextDp[1] = Math.max(dp[0], dp[1]) + nums[i];
      }

      dp = nextDp;
    }

    return Math.max(dp[0], dp[1]);
  }
}