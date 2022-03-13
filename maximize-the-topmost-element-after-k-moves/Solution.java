import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maximumTop(int[] nums, int k) {
    if (k == 0) {
      return nums[0];
    }
    if (k == 1) {
      return (nums.length == 1) ? -1 : nums[1];
    }
    if (k <= nums.length) {
      int result = IntStream.range(0, k - 1).map(i -> nums[i]).max().getAsInt();
      if (k != nums.length) {
        result = Math.max(result, nums[k]);
      }

      return result;
    }
    if (k % 2 == (nums.length + 1) % 2) {
      return Arrays.stream(nums).max().getAsInt();
    }

    return (nums.length >= 2) ? Arrays.stream(nums).max().getAsInt() : -1;
  }
}