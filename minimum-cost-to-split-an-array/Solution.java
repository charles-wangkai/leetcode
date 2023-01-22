import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
  public int minCost(int[] nums, int k) {
    int[] dp = new int[nums.length + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 1; i < dp.length; ++i) {
      Set<Integer> singles = new HashSet<>();
      Set<Integer> multiples = new HashSet<>();
      for (int j = i - 1; j >= 0; --j) {
        if (!multiples.contains(nums[j])) {
          if (singles.contains(nums[j])) {
            singles.remove(nums[j]);
            multiples.add(nums[j]);
          } else {
            singles.add(nums[j]);
          }
        }

        dp[i] = Math.min(dp[i], dp[j] + k + ((i - j) - singles.size()));
      }
    }

    return dp[nums.length];
  }
}
