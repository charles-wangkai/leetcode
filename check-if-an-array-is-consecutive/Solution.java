import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public boolean isConsecutive(int[] nums) {
    Arrays.sort(nums);

    return IntStream.range(0, nums.length - 1).allMatch(i -> nums[i] + 1 == nums[i + 1]);
  }
}
