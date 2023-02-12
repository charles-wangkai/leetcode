class Solution {
  public long findTheArrayConcVal(int[] nums) {
    long result = 0;
    for (int i = 0, j = nums.length - 1; i <= j; ++i, --j) {
      if (i == j) {
        result += nums[i];
      } else {
        result += Integer.parseInt(String.valueOf(nums[i]) + String.valueOf(nums[j]));
      }
    }

    return result;
  }
}
