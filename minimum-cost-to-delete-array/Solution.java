import java.util.stream.IntStream;

class Solution {
  public long minCost(int[] nums) {
    int[] sorted = IntStream.range(1, nums.length - 1).map(i -> nums[i]).sorted().toArray();

    return nums[0]
        + nums[nums.length - 1]
        + IntStream.range(0, sorted.length / 2)
            .map(i -> Math.min(0, sorted[i * 2] + sorted[i * 2 + 1]))
            .asLongStream()
            .sum();
  }
}