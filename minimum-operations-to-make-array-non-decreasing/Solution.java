import java.util.stream.IntStream;

class Solution {
  public long minOperations(int[] nums) {
    return IntStream.range(0, nums.length - 1)
        .map(i -> Math.max(0, nums[i] - nums[i + 1]))
        .asLongStream()
        .sum();
  }
}
