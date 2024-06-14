import java.util.Arrays;

class Solution {
  public int minIncrementForUnique(int[] nums) {
    Arrays.sort(nums);

    int result = 0;
    int lower = Integer.MIN_VALUE;
    for (int num : nums) {
      lower = Math.max(lower, num);
      result += lower - num;

      ++lower;
    }

    return result;
  }
}
