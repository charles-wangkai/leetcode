class Solution {
  public int longestSubarray(int[] nums) {
    int result = 0;
    int length = 0;
    for (int i = 0; i < nums.length; ++i) {
      if (i <= 1 || nums[i] == nums[i - 1] + nums[i - 2]) {
        ++length;
      } else {
        length = 2;
      }

      result = Math.max(result, length);
    }

    return result;
  }
}