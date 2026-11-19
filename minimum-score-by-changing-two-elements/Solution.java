import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minimizeSum(int[] nums) {
    Arrays.sort(nums);

    return IntStream.of(
            nums[nums.length - 1] - nums[2],
            nums[nums.length - 2] - nums[1],
            nums[nums.length - 3] - nums[0])
        .min()
        .getAsInt();
  }
}
