import java.util.stream.IntStream;

class Solution {
  public boolean isMonotonic(int[] nums) {
    return IntStream.range(0, nums.length - 1).allMatch(i -> nums[i] <= nums[i + 1])
        || IntStream.range(0, nums.length - 1).allMatch(i -> nums[i] >= nums[i + 1]);
  }
}
