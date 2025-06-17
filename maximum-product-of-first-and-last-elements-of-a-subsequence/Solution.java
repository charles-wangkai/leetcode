class Solution {
  public long maximumProduct(int[] nums, int m) {
    long result = Long.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = m - 1; i < nums.length; ++i) {
      min = Math.min(min, nums[i - m + 1]);
      max = Math.max(max, nums[i - m + 1]);

      result = Math.max(result, Math.max((long) nums[i] * min, (long) nums[i] * max));
    }

    return result;
  }
}