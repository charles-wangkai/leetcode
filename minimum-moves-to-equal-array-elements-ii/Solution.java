import java.util.Arrays;

class Solution {
  public int minMoves2(int[] nums) {
    Arrays.sort(nums);

    int result = 0;
    for (int i = 0, j = nums.length - 1; i < j; ++i, --j) {
      result += nums[j] - nums[i];
    }

    return result;
  }
}
