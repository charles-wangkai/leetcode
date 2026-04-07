import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minOperations(int[] nums, int k) {
    if (k == 0) {
      return 0;
    }
    if (nums.length < k * 2) {
      return -1;
    }

    return Math.min(
        computeOperationNum(k, nums),
        computeOperationNum(
            k,
            IntStream.concat(
                    IntStream.range(1, nums.length).map(i -> nums[i]), IntStream.of(nums[0]))
                .toArray()));
  }

  int computeOperationNum(int k, int[] values) {
    int n = values.length;

    int[][] dp = new int[n][n + 1];
    Arrays.fill(dp[0], Integer.MAX_VALUE);

    for (int i = 1; i < dp.length; ++i) {
      for (int peakNum = 0; peakNum <= n; ++peakNum) {
        dp[i][peakNum] = dp[i - 1][peakNum];
      }

      int operationNum =
          Math.max(0, Math.max(values[i - 1], values[(i + 1) % values.length]) + 1 - values[i]);

      dp[i][1] = Math.min(dp[i][1], operationNum);

      if (i >= 2) {
        for (int peakNum = 0; peakNum <= n; ++peakNum) {
          if (dp[i - 2][peakNum] != Integer.MAX_VALUE) {
            dp[i][peakNum + 1] = Math.min(dp[i][peakNum + 1], dp[i - 2][peakNum] + operationNum);
          }
        }
      }
    }

    int result = Integer.MAX_VALUE;
    for (int i = 0; i < dp.length; ++i) {
      for (int peakNum = k; peakNum <= n; ++peakNum) {
        result = Math.min(result, dp[i][peakNum]);
      }
    }

    return result;
  }
}