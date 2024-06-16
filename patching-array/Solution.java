class Solution {
  public int minPatches(int[] nums, int n) {
    int result = 0;
    long next = 1;
    int index = 0;
    while (next <= n) {
      if (index != nums.length && nums[index] <= next) {
        next += nums[index];
        ++index;
      } else {
        next += next;
        ++result;
      }
    }

    return result;
  }
}
