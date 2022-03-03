class Solution {
  public int numberOfArithmeticSlices(int[] nums) {
    int result = 0;
    int prevDiff = Integer.MIN_VALUE;
    int count = 0;
    for (int i = 1; i < nums.length; ++i) {
      int diff = nums[i] - nums[i - 1];
      if (diff == prevDiff) {
        ++count;
      } else {
        prevDiff = diff;
        count = 1;
      }

      result += Math.max(0, count - 1);
    }

    return result;
  }
}
