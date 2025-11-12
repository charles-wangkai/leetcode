class Solution {
  public int minimumDistance(int[] nums) {
    int result = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i + 1; j < nums.length; ++j) {
        for (int k = j + 1; k < nums.length; ++k) {
          if (nums[i] == nums[j] && nums[j] == nums[k]) {
            result = Math.min(result, (j - i) + (k - j) + (k - i));
          }
        }
      }
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }
}