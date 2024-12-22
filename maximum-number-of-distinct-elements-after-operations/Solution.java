import java.util.Arrays;

class Solution {
  public int maxDistinctElements(int[] nums, int k) {
    Arrays.sort(nums);

    for (int i = 0; i < nums.length; ++i) {
      nums[i] =
          Math.min(
              nums[i] + k, Math.max(nums[i] - k, (i == 0) ? Integer.MIN_VALUE : (nums[i - 1] + 1)));
    }

    return (int) Arrays.stream(nums).distinct().count();
  }
}