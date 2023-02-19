import java.util.Arrays;

class Solution {
  public int minimizeSum(int[] nums) {
    Arrays.sort(nums);

    return Math.min(
        Math.min(nums[nums.length - 1] - nums[2], nums[nums.length - 3] - nums[0]),
        nums[nums.length - 2] - nums[1]);
  }
}
