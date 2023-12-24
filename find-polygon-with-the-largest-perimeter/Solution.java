import java.util.Arrays;

class Solution {
  public long largestPerimeter(int[] nums) {
    Arrays.sort(nums);

    long result = -1;
    long sum = 0;
    for (int i = 0; i < nums.length; ++i) {
      if (i >= 2 && nums[i] < sum) {
        result = sum + nums[i];
      }

      sum += nums[i];
    }

    return result;
  }
}