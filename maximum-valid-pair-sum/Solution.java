class Solution {
  public int maxValidPairSum(int[] nums, int k) {
    int result = Integer.MIN_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = k; i < nums.length; ++i) {
      max = Math.max(max, nums[i - k]);
      result = Math.max(result, max + nums[i]);
    }

    return result;
  }
}