class Solution {
  public int maximumDifference(int[] nums) {
    int result = -1;
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i + 1; j < nums.length; ++j) {
        if (nums[j] > nums[i]) {
          result = Math.max(result, nums[j] - nums[i]);
        }
      }
    }

    return result;
  }
}
