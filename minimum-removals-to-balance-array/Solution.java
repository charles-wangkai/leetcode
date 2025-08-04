import java.util.Arrays;

class Solution {
  public int minRemoval(int[] nums, int k) {
    Arrays.sort(nums);

    int result = Integer.MAX_VALUE;
    int endIndex = 0;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      while (endIndex != nums.length - 1 && nums[endIndex + 1] <= (long) nums[beginIndex] * k) {
        ++endIndex;
      }

      result = Math.min(result, nums.length - (endIndex - beginIndex + 1));
    }

    return result;
  }
}