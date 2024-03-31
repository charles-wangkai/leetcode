class Solution {
  public long countAlternatingSubarrays(int[] nums) {
    long result = 0;
    int beginIndex = 0;
    while (beginIndex != nums.length) {
      int endIndex = beginIndex;
      while (endIndex != nums.length - 1 && nums[endIndex + 1] != nums[endIndex]) {
        ++endIndex;
      }

      result += (endIndex - beginIndex + 1L) * (endIndex - beginIndex + 2) / 2;

      beginIndex = endIndex + 1;
    }

    return result;
  }
}