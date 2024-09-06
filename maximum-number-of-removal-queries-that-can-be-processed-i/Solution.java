// https://leetcode.com/problems/maximum-number-of-removal-queries-that-can-be-processed-i/solutions/4662822/interesting-problem/

class Solution {
  public int maximumProcessableQueries(int[] nums, int[] queries) {
    int[][] dp = new int[nums.length][nums.length + 1];
    for (int length = nums.length; length >= 0; --length) {
      for (int beginIndex = 0;
          beginIndex < dp.length && beginIndex + length <= nums.length;
          ++beginIndex) {
        int endIndex = beginIndex + length;
        dp[beginIndex][endIndex] =
            Math.max(
                (beginIndex == 0)
                    ? 0
                    : (dp[beginIndex - 1][endIndex]
                        + ((dp[beginIndex - 1][endIndex] != queries.length
                                && nums[beginIndex - 1] >= queries[dp[beginIndex - 1][endIndex]])
                            ? 1
                            : 0)),
                (endIndex == nums.length)
                    ? 0
                    : (dp[beginIndex][endIndex + 1]
                        + ((dp[beginIndex][endIndex + 1] != queries.length
                                && nums[endIndex] >= queries[dp[beginIndex][endIndex + 1]])
                            ? 1
                            : 0)));
      }
    }

    int result = 0;
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i; j < nums.length; ++j) {
        result = Math.max(result, dp[i][j]);
      }
    }

    return result;
  }
}