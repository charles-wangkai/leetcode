class Solution {
  public int longestAlternatingSubarray(int[] nums, int threshold) {
    int result = 0;
    for (int l = 0; l < nums.length; ++l) {
      if (nums[l] % 2 == 0) {
        for (int r = l; r < nums.length; ++r) {
          if ((r != l && nums[r] % 2 == nums[r - 1] % 2) || nums[r] > threshold) {
            break;
          }

          result = Math.max(result, r - l + 1);
        }
      }
    }

    return result;
  }
}
