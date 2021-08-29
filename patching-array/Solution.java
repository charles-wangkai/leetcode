class Solution {
  public int minPatches(int[] nums, int n) {
    long miss = 1;
    int result = 0;
    int index = 0;
    while (miss <= n) {
      if (index != nums.length && nums[index] <= miss) {
        miss += nums[index];
        ++index;
      } else {
        miss += miss;
        ++result;
      }
    }

    return result;
  }
}
