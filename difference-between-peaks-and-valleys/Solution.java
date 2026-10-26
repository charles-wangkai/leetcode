import java.util.stream.IntStream;

class Solution {
  public int peakValleyDifference(int[] nums) {
    return IntStream.range(1, nums.length - 1)
            .filter(i -> nums[i] > nums[i - 1] && nums[i] > nums[i + 1])
            .map(i -> nums[i])
            .sum()
        - IntStream.range(1, nums.length - 1)
            .filter(i -> nums[i] < nums[i - 1] && nums[i] < nums[i + 1])
            .map(i -> nums[i])
            .sum();
  }
}