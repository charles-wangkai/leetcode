import java.util.Arrays;

class Solution {
  public int maximumBeauty(int[] nums, int k) {
    Arrays.sort(nums);

    int result = 0;
    int endIndex = 0;
    for (int i = 0; i < nums.length; ++i) {
      while (endIndex != nums.length - 1 && nums[endIndex + 1] - nums[i] <= 2 * k) {
        ++endIndex;
      }

      result = Math.max(result, endIndex - i + 1);
    }

    return result;
  }
}
