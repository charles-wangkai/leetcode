class Solution {
  public int minimumSubarrayLength(int[] nums, int k) {
    int result = Integer.MAX_VALUE;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      int or = 0;
      for (int endIndex = beginIndex; endIndex < nums.length; ++endIndex) {
        or |= nums[endIndex];
        if (or >= k) {
          result = Math.min(result, endIndex - beginIndex + 1);
        }
      }
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }
}