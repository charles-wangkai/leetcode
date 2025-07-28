import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long maximumMedianSum(int[] nums) {
    Arrays.sort(nums);

    return IntStream.range(0, nums.length / 3)
        .map(i -> nums[nums.length - 2 - i * 2])
        .asLongStream()
        .sum();
  }
}