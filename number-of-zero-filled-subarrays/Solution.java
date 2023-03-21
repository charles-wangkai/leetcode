class Solution {
  public long zeroFilledSubarray(int[] nums) {
    long result = 0;
    int count = 0;
    for (int i = 0; i <= nums.length; ++i) {
      if (i != nums.length && nums[i] == 0) {
        ++count;
      } else {
        result += count * (count + 1L) / 2;
        count = 0;
      }
    }

    return result;
  }
}
