import java.util.Arrays;

class Solution {
  public int maxDistinctElements(int[] nums, int k) {
    Arrays.sort(nums);

    for (int i = 0; i < nums.length; ++i) {
      nums[i] =
          Math.clamp((i == 0) ? Integer.MIN_VALUE : (nums[i - 1] + 1), nums[i] - k, nums[i] + k);
    }

    return (int) Arrays.stream(nums).distinct().count();
  }
}