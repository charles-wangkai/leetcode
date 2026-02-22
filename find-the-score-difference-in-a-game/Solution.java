class Solution {
  public int scoreDifference(int[] nums) {
    int result = 0;
    int sign = 1;
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] % 2 == 1) {
        sign *= -1;
      }
      if (i % 6 == 5) {
        sign *= -1;
      }

      result += sign * nums[i];
    }

    return result;
  }
}