import java.util.Arrays;

class Solution {
  public int maxSubarrays(int[] nums) {
    if (Arrays.stream(nums).reduce((x, y) -> x & y).getAsInt() != 0) {
      return 1;
    }

    int result = 0;
    int and = -1;
    for (int num : nums) {
      and = (and == -1) ? num : (and & num);
      if (and == 0) {
        ++result;
        and = -1;
      }
    }

    return result;
  }
}
