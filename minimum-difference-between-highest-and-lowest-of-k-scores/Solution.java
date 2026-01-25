import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minimumDifference(int[] nums, int k) {
    Arrays.sort(nums);

    return IntStream.rangeClosed(0, nums.length - k)
        .map(i -> nums[i + k - 1] - nums[i])
        .min()
        .getAsInt();
  }
}
