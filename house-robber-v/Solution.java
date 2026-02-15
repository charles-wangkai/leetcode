class Solution {
  public long rob(int[] nums, int[] colors) {
    long prev = 0;
    long curr = 0;
    for (int i = 0; i < nums.length; ++i) {
      long next = Math.max(prev + nums[i], curr);
      if (!(i != 0 && colors[i] == colors[i - 1])) {
        next = Math.max(next, curr + nums[i]);
      }

      prev = curr;
      curr = next;
    }

    return curr;
  }
}