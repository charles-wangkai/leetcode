import java.util.stream.IntStream;

class Solution {
  public int maxSumAfterOperation(int[] nums) {
    int[] leftMaxSums = new int[nums.length];
    int leftMaxSum = 0;
    for (int i = 0; i < leftMaxSums.length; ++i) {
      leftMaxSum = Math.max(0, nums[i] + leftMaxSum);
      leftMaxSums[i] = leftMaxSum;
    }

    int[] rightMaxSums = new int[nums.length];
    int rightMaxSum = 0;
    for (int i = rightMaxSums.length - 1; i >= 0; --i) {
      rightMaxSum = Math.max(0, nums[i] + rightMaxSum);
      rightMaxSums[i] = rightMaxSum;
    }

    return IntStream.range(0, nums.length)
        .map(
            i ->
                nums[i] * nums[i]
                    + ((i == 0) ? 0 : leftMaxSums[i - 1])
                    + ((i == nums.length - 1) ? 0 : rightMaxSums[i + 1]))
        .max()
        .getAsInt();
  }
}
