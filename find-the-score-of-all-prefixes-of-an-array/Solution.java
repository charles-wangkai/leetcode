class Solution {
  public long[] findPrefixScore(int[] nums) {
    int[] conversions = new int[nums.length];
    int maxValue = Integer.MIN_VALUE;
    for (int i = 0; i < conversions.length; ++i) {
      maxValue = Math.max(maxValue, nums[i]);
      conversions[i] = nums[i] + maxValue;
    }

    long[] result = new long[nums.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] = ((i == 0) ? i : result[i - 1]) + conversions[i];
    }

    return result;
  }
}
