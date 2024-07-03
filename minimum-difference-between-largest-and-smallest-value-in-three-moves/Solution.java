import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minDifference(int[] nums) {
    if (nums.length <= 4) {
      return 0;
    }

    Arrays.sort(nums);

    return IntStream.rangeClosed(0, 3)
        .map(i -> nums[i + nums.length - 4] - nums[i])
        .min()
        .getAsInt();
  }
}