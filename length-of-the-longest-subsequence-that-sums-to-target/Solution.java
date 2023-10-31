import java.util.Arrays;
import java.util.List;

class Solution {
  public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
    int[] dp = new int[target + 1];
    Arrays.fill(dp, -1);
    dp[0] = 0;

    for (int num : nums) {
      for (int i = target - num; i >= 0; --i) {
        if (dp[i] != -1) {
          dp[i + num] = Math.max(dp[i + num], dp[i] + 1);
        }
      }
    }

    return dp[target];
  }
}
