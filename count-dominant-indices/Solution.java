class Solution {
  public int dominantIndices(int[] nums) {
    int result = 0;
    int sum = 0;
    for (int i = nums.length - 2; i >= 0; --i) {
      sum += nums[i + 1];
      if (nums[i] * (nums.length - i - 1) > sum) {
        ++result;
      }
    }

    return result;
  }
}