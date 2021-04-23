class Solution {
  public int minOperations(int[] nums) {
    int result = 0;
    for (int i = 1; i < nums.length; ++i) {
      int delta = Math.max(0, nums[i - 1] + 1 - nums[i]);
      nums[i] += delta;
      result += delta;
    }

    return result;
  }
}
