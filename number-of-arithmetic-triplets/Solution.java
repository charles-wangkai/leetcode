class Solution {
  public int arithmeticTriplets(int[] nums, int diff) {
    int result = 0;
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i + 1; j < nums.length; ++j) {
        if (nums[j] - nums[i] == diff) {
          for (int k = j + 1; k < nums.length; ++k) {
            if (nums[k] - nums[j] == diff) {
              ++result;
            }
          }
        }
      }
    }

    return result;
  }
}