import java.util.Arrays;

class Solution {
  public long maxSubarraySum(int[] nums, int k) {
    return Math.max(computeMaxSum(nums, k, true), computeMaxSum(nums, k, false));
  }

  long computeMaxSum(int[] nums, int k, boolean multiplyOrDivide) {
    long maxSum = Long.MIN_VALUE;

    long[][] dp = new long[3][2];
    for (int operationStatus = 0; operationStatus <= 2; ++operationStatus) {
      Arrays.fill(dp[operationStatus], Long.MIN_VALUE);
    }
    dp[0][0] = 0;

    for (int num : nums) {
      long modified = multiplyOrDivide ? ((long) num * k) : (num / k);

      long[][] nextDp = new long[3][2];
      for (int operationStatus = 0; operationStatus <= 2; ++operationStatus) {
        Arrays.fill(nextDp[operationStatus], Long.MIN_VALUE);
      }

      for (int operationStatus = 0; operationStatus <= 2; ++operationStatus) {
        for (int inSum = 0; inSum <= 1; ++inSum) {
          if (dp[operationStatus][inSum] != Long.MIN_VALUE) {
            if (operationStatus == 2) {
              if (inSum == 1) {
                maxSum = update(maxSum, nextDp, 2, 1, dp[operationStatus][inSum] + num);
              } else {
                maxSum = update(maxSum, nextDp, 2, 0, dp[operationStatus][inSum]);
                maxSum = update(maxSum, nextDp, 2, 1, dp[operationStatus][inSum] + num);
              }
            } else if (operationStatus == 1) {
              if (inSum == 1) {
                maxSum = update(maxSum, nextDp, 1, 1, dp[operationStatus][inSum] + modified);
                maxSum = update(maxSum, nextDp, 2, 1, dp[operationStatus][inSum] + num);
              } else {
                maxSum = update(maxSum, nextDp, 1, 0, dp[operationStatus][inSum]);
                maxSum = update(maxSum, nextDp, 2, 0, dp[operationStatus][inSum]);
                maxSum = update(maxSum, nextDp, 1, 1, dp[operationStatus][inSum] + modified);
                maxSum = update(maxSum, nextDp, 2, 1, dp[operationStatus][inSum] + num);
              }
            } else if (inSum == 1) {
              maxSum = update(maxSum, nextDp, 0, 1, dp[operationStatus][inSum] + num);
              maxSum = update(maxSum, nextDp, 1, 1, dp[operationStatus][inSum] + modified);
            } else {
              maxSum = update(maxSum, nextDp, 0, 0, dp[operationStatus][inSum]);
              maxSum = update(maxSum, nextDp, 1, 0, dp[operationStatus][inSum]);
              maxSum = update(maxSum, nextDp, 0, 1, dp[operationStatus][inSum] + num);
              maxSum = update(maxSum, nextDp, 1, 1, dp[operationStatus][inSum] + modified);
            }
          }
        }
      }

      dp = nextDp;
    }

    return maxSum;
  }

  long update(long maxSum, long[][] nextDp, int nextOperationStatus, int nextInSum, long nextSum) {
    if (nextOperationStatus >= 0 && nextInSum == 1) {
      maxSum = Math.max(maxSum, nextSum);
    }

    nextDp[nextOperationStatus][nextInSum] =
        Math.max(nextDp[nextOperationStatus][nextInSum], nextSum);

    return maxSum;
  }
}