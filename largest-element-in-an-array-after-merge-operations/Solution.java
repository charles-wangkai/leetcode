class Solution {
  public long maxArrayValue(int[] nums) {
    long result = 0;
    long sum = 0;
    for (int i = nums.length - 1; i >= 0; --i) {
      if (nums[i] <= sum) {
        sum += nums[i];
      } else {
        sum = nums[i];
      }

      result = Math.max(result, sum);
    }

    return result;
  }
}
