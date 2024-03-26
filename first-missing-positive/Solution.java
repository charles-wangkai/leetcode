class Solution {
  public int firstMissingPositive(int[] nums) {
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] <= 0) {
        nums[i] = Integer.MAX_VALUE;
      }
    }

    for (int i = 0; i < nums.length; ++i) {
      int original = Math.abs(nums[i]);
      if (original <= nums.length) {
        nums[original - 1] = -Math.abs(nums[original - 1]);
      }
    }

    int result = 1;
    while (result - 1 < nums.length && nums[result - 1] < 0) {
      ++result;
    }

    return result;
  }
}
