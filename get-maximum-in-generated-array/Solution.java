import java.util.Arrays;

class Solution {
  public int getMaximumGenerated(int n) {
    int[] nums = new int[n + 1];
    for (int i = 0; i < nums.length; ++i) {
      if (i <= 1) {
        nums[i] = i;
      } else if (i % 2 == 0) {
        nums[i] = nums[i / 2];
      } else {
        nums[i] = nums[i / 2] + nums[i / 2 + 1];
      }
    }

    return Arrays.stream(nums).max().getAsInt();
  }
}
