class Solution {
  public int rob(int[] nums) {
    return Math.max(rob(nums, 1, nums.length - 1), nums[0] + rob(nums, 2, nums.length - 2));
  }

  int rob(int[] nums, int beginIndex, int endIndex) {
    if (beginIndex > endIndex) {
      return 0;
    }

    int prev2 = 0;
    int prev1 = 0;
    for (int i = beginIndex; i <= endIndex; ++i) {
      int current = Math.max(prev2 + nums[i], prev1);
      prev2 = prev1;
      prev1 = current;
    }

    return prev1;
  }
}
