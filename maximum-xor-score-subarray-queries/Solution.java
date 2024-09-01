// https://leetcode.com/problems/maximum-xor-score-subarray-queries/solutions/5718367/easier-than-it-looks-intuition-and-logic/

import java.util.Arrays;

class Solution {
  public int[] maximumSubarrayXor(int[] nums, int[][] queries) {
    int n = nums.length;

    int[][] scores = buildScores(nums);

    int[][] dp = new int[n][n];
    for (int i = 0; i < n; ++i) {
      dp[i][i] = scores[i][i];
    }
    for (int length = 2; length <= n; ++length) {
      for (int beginIndex = 0; beginIndex + length <= n; ++beginIndex) {
        int endIndex = beginIndex + length - 1;
        dp[beginIndex][endIndex] =
            Math.max(
                Math.max(dp[beginIndex + 1][endIndex], dp[beginIndex][endIndex - 1]),
                scores[beginIndex][endIndex]);
      }
    }

    return Arrays.stream(queries).mapToInt(query -> dp[query[0]][query[1]]).toArray();
  }

  int[][] buildScores(int[] nums) {
    int n = nums.length;

    int[][] result = new int[n][n];
    for (int i = 0; i < n; ++i) {
      result[i][i] = nums[i];
    }
    for (int length = 2; length <= n; ++length) {
      for (int beginIndex = 0; beginIndex + length <= n; ++beginIndex) {
        int endIndex = beginIndex + length - 1;
        result[beginIndex][endIndex] =
            result[beginIndex + 1][endIndex] ^ result[beginIndex][endIndex - 1];
      }
    }

    return result;
  }
}