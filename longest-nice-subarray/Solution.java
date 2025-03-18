class Solution {
  public int longestNiceSubarray(int[] nums) {
    int result = 0;
    int endIndex = -1;
    int or = 0;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      while (endIndex + 1 != nums.length && (or & nums[endIndex + 1]) == 0) {
        ++endIndex;
        or |= nums[endIndex];
      }

      result = Math.max(result, endIndex - beginIndex + 1);
      or ^= nums[beginIndex];
    }

    return result;
  }
}