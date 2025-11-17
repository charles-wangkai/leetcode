import java.util.Arrays;

class Solution {
  public int maximizeExpressionOfThree(int[] nums) {
    Arrays.sort(nums);

    return nums[nums.length - 1] + nums[nums.length - 2] - nums[0];
  }
}