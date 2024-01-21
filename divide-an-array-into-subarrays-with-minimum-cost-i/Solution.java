class Solution {
  public int minimumCost(int[] nums) {
    int result = Integer.MAX_VALUE;
    for (int i = 1; i < nums.length; ++i) {
      for (int j = i + 1; j < nums.length; ++j) {
        result = Math.min(result, nums[0] + nums[i] + nums[j]);
      }
    }

    return result;
  }
}