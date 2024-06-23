import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public double minimumAverage(int[] nums) {
    Arrays.sort(nums);

    return IntStream.range(0, nums.length / 2)
            .map(i -> nums[i] + nums[nums.length - 1 - i])
            .min()
            .getAsInt()
        / 2.0;
  }
}