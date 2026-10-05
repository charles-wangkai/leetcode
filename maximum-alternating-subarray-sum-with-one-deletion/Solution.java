import java.util.Arrays;

class Solution {
  public long maxAlternatingSum(int[] nums) {
    int n = nums.length;

    long[] leftOddMaxSums = new long[n];
    Arrays.fill(leftOddMaxSums, Long.MIN_VALUE);

    long[] leftEvenMaxSums = new long[n];
    Arrays.fill(leftEvenMaxSums, Long.MIN_VALUE);

    long leftSum = 0;
    long prefixOddMaxSum = Long.MIN_VALUE;
    long prefixEvenMinSum = 0;
    for (int i = 0; i < n; ++i) {
      if (i % 2 == 0) {
        leftSum += nums[i];

        leftOddMaxSums[i] = leftSum - prefixEvenMinSum;
        if (prefixOddMaxSum != Long.MIN_VALUE) {
          leftEvenMaxSums[i] = -(leftSum - prefixOddMaxSum);
        }

        prefixOddMaxSum = Math.max(prefixOddMaxSum, leftSum);
      } else {
        leftSum -= nums[i];

        if (prefixOddMaxSum != Long.MIN_VALUE) {
          leftOddMaxSums[i] = -(leftSum - prefixOddMaxSum);
        }
        leftEvenMaxSums[i] = leftSum - prefixEvenMinSum;

        prefixEvenMinSum = Math.min(prefixEvenMinSum, leftSum);
      }
    }

    long[] rightPosMaxSums = new long[n];
    Arrays.fill(rightPosMaxSums, Long.MIN_VALUE);

    long[] rightNegMaxSums = new long[n];
    Arrays.fill(rightNegMaxSums, Long.MIN_VALUE);

    long rightSum = 0;
    long suffixOddMinSum = Long.MAX_VALUE;
    long suffixOddMaxSum = Long.MIN_VALUE;
    long suffixEvenMinSum = 0;
    long suffixEvenMaxSum = 0;
    for (int i = n - 1; i >= 0; --i) {
      if ((n - 1 - i) % 2 == 0) {
        rightSum += nums[i];

        rightPosMaxSums[i] =
            Math.max(
                (suffixOddMinSum == Long.MAX_VALUE) ? Long.MIN_VALUE : (rightSum - suffixOddMinSum),
                rightSum - suffixEvenMinSum);
        rightNegMaxSums[i] =
            Math.max(
                (suffixOddMaxSum == Long.MIN_VALUE)
                    ? Long.MIN_VALUE
                    : -(rightSum - suffixOddMaxSum),
                -(rightSum - suffixEvenMaxSum));

        suffixOddMinSum = Math.min(suffixOddMinSum, rightSum);
        suffixOddMaxSum = Math.max(suffixOddMaxSum, rightSum);
      } else {
        rightSum -= nums[i];

        rightPosMaxSums[i] =
            Math.max(
                (suffixOddMaxSum == Long.MIN_VALUE)
                    ? Long.MIN_VALUE
                    : -(rightSum - suffixOddMaxSum),
                -(rightSum - suffixEvenMaxSum));
        rightNegMaxSums[i] =
            Math.max(
                (suffixOddMinSum == Long.MAX_VALUE) ? Long.MIN_VALUE : (rightSum - suffixOddMinSum),
                rightSum - suffixEvenMinSum);

        suffixEvenMinSum = Math.min(suffixEvenMinSum, rightSum);
        suffixEvenMaxSum = Math.max(suffixEvenMaxSum, rightSum);
      }
    }

    long result = Long.MIN_VALUE;
    for (int i = 0; i < n; ++i) {
      result = Math.max(result, Math.max(leftOddMaxSums[i], leftEvenMaxSums[i]));
    }
    for (int i = 1; i < n - 1; ++i) {
      if (leftOddMaxSums[i - 1] != Long.MIN_VALUE && rightNegMaxSums[i + 1] != Long.MIN_VALUE) {
        result = Math.max(result, leftOddMaxSums[i - 1] + rightNegMaxSums[i + 1]);
      }
      if (leftEvenMaxSums[i - 1] != Long.MIN_VALUE && rightPosMaxSums[i + 1] != Long.MIN_VALUE) {
        result = Math.max(result, leftEvenMaxSums[i - 1] + rightPosMaxSums[i + 1]);
      }
    }

    return result;
  }
}