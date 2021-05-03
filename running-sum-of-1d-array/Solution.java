class Solution {
  public int[] runningSum(int[] nums) {
    int[] result = new int[nums.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] = ((i == 0) ? 0 : result[i - 1]) + nums[i];
    }

    return result;
  }
}
