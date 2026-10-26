class Solution {
  public long countSubarrays(int[] nums, int minK, int maxK) {
    long result = 0;
    int count = 0;
    for (int i = 0; i <= nums.length; ++i) {
      if (i != nums.length && nums[i] >= minK && nums[i] <= maxK) {
        ++count;
      } else {
        result += count * (count + 1L) / 2;
        count = 0;
      }
    }

    return result;
  }
}