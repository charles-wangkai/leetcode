class Solution {
  public int countMajoritySubarrays(int[] nums, int target) {
    int result = 0;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      int count = 0;
      for (int endIndex = beginIndex; endIndex < nums.length; ++endIndex) {
        if (nums[endIndex] == target) {
          ++count;
        }

        if (count * 2 > endIndex - beginIndex + 1) {
          ++result;
        }
      }
    }

    return result;
  }
}