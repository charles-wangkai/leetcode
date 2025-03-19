class Solution {
  public int minOperations(int[] nums) {
    int result = 0;
    for (int i = 0; i <= nums.length - 3; ++i) {
      if (nums[i] == 0) {
        for (int j = 0; j < 3; ++j) {
          nums[i + j] = 1 - nums[i + j];
        }
        ++result;
      }
    }

    return (nums[nums.length - 2] == 1 && nums[nums.length - 1] == 1) ? result : -1;
  }
}