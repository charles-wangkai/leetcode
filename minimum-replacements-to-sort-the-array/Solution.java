class Solution {
  public long minimumReplacement(int[] nums) {
    long result = 0;
    for (int i = nums.length - 2; i >= 0; --i) {
      int size = (nums[i] + nums[i + 1] - 1) / nums[i + 1];
      result += size - 1;
      nums[i] /= size;
    }

    return result;
  }
}