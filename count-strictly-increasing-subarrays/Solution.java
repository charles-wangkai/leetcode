class Solution {
  public long countSubarrays(int[] nums) {
    long result = 0;
    int length = 1;
    for (int i = 1; i <= nums.length; ++i) {
      if (i != nums.length && nums[i] > nums[i - 1]) {
        ++length;
      } else {
        result += length * (length + 1L) / 2;
        length = 1;
      }
    }

    return result;
  }
}
