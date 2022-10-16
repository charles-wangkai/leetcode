class Solution {
  public int minimizeArrayValue(int[] nums) {
    int result = -1;
    long sum = 0;
    for (int i = 0; i < nums.length; ++i) {
      sum += nums[i];
      result = Math.max(result, (int) ((sum + i) / (i + 1)));
    }

    return result;
  }
}
