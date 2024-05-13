// https://leetcode.com/problems/find-the-minimum-cost-array-permutation/solutions/5146002/dp-with-tracking/

import java.util.Arrays;

class Solution {
  public int[] findPermutation(int[] nums) {
    int n = nums.length;

    int[][] dp = new int[n][1 << n];
    for (int i = 0; i < dp.length; ++i) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }

    int[][] nexts = new int[n][1 << n];

    search(nums, dp, nexts, 1, 0);

    int[] result = new int[n];
    int mask = 1;
    for (int i = 1; i < result.length; ++i) {
      result[i] = nexts[result[i - 1]][mask];
      mask += 1 << result[i];
    }

    return result;
  }

  int search(int[] nums, int[][] dp, int[][] nexts, int mask, int last) {
    int n = nums.length;

    if (mask == (1 << n) - 1) {
      return Math.abs(last - nums[0]);
    }

    if (dp[last][mask] == Integer.MAX_VALUE) {
      for (int i = 1; i < n; ++i) {
        if (((mask >> i) & 1) == 0) {
          int cost = Math.abs(last - nums[i]) + search(nums, dp, nexts, mask + (1 << i), i);
          if (cost < dp[last][mask]) {
            dp[last][mask] = cost;
            nexts[last][mask] = i;
          }
        }
      }
    }

    return dp[last][mask];
  }
}
