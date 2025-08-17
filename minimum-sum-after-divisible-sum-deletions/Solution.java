import java.util.HashMap;
import java.util.Map;

class Solution {
  public long minArraySum(int[] nums, int k) {
    long[] dp = new long[nums.length + 1];
    int remainder = 0;
    Map<Integer, Integer> remainderToLength = new HashMap<>();
    remainderToLength.put(0, 0);

    for (int i = 1; i < dp.length; ++i) {
      dp[i] = dp[i - 1] + nums[i - 1];

      remainder = (remainder + nums[i - 1]) % k;
      if (remainderToLength.containsKey(remainder)) {
        dp[i] = Math.min(dp[i], dp[remainderToLength.get(remainder)]);
      }

      remainderToLength.put(remainder, i);
    }

    return dp[dp.length - 1];
  }
}