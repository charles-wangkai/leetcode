import java.util.stream.IntStream;

class Solution {
  public long maxValue(int[] nums) {
    int n = nums.length;

    return IntStream.range(0, nums.length)
            .map(i -> ((i % 2 == 0) ? 1 : -1) * nums[i])
            .asLongStream()
            .sum()
        + Math.max(
            computeMaxSubarraySum(
                IntStream.range(0, n / 2)
                    .mapToLong(i -> -2L * (nums[i * 2] - nums[i * 2 + 1]))
                    .toArray()),
            computeMaxSubarraySum(
                IntStream.range(0, (n - 1) / 2)
                    .mapToLong(i -> -2L * (-nums[i * 2 + 1] + nums[i * 2 + 2]))
                    .toArray()));
  }

  long computeMaxSubarraySum(long[] values) {
    long result = 0;
    long sum = 0;
    for (long value : values) {
      sum += value;
      result = Math.max(result, sum);
      sum = Math.max(0, sum);
    }

    return result;
  }
}