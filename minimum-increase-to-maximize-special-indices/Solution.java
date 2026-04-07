import java.util.stream.IntStream;

class Solution {
  public long minIncrease(int[] nums) {
    int[] operationNums = new int[nums.length];
    for (int i = 1; i < operationNums.length - 1; ++i) {
      operationNums[i] = Math.max(0, Math.max(nums[i - 1], nums[i + 1]) + 1 - nums[i]);
    }

    long[] leftSums = new long[nums.length];
    for (int i = 1; i < leftSums.length; i += 2) {
      leftSums[i] = ((i == 1) ? 0 : leftSums[i - 2]) + operationNums[i];
    }

    if (nums.length % 2 == 1) {
      return leftSums[leftSums.length - 2];
    }

    long[] rightSums = new long[nums.length];
    for (int i = rightSums.length - 2; i >= 0; i -= 2) {
      rightSums[i] = ((i == rightSums.length - 2) ? 0 : rightSums[i + 2]) + operationNums[i];
    }

    return Math.min(
        Math.min(leftSums[leftSums.length - 3], rightSums[2]),
        IntStream.rangeClosed(1, nums.length - 5)
            .filter(i -> i % 2 == 1)
            .mapToLong(i -> leftSums[i] + rightSums[i + 3])
            .min()
            .orElse(Long.MAX_VALUE));
  }
}