import java.util.Arrays;

class Solution {
  public int minCapability(int[] nums, int k) {
    int result = -1;
    int lower = 0;
    int upper = Arrays.stream(nums).max().getAsInt();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, k, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int k, int capability) {
    int[] dp = new int[nums.length];
    for (int i = 0; i < dp.length; ++i) {
      dp[i] =
          Math.max(
              ((nums[i] <= capability) ? 1 : 0) + ((i >= 2) ? dp[i - 2] : 0),
              (i >= 1) ? dp[i - 1] : 0);
    }

    return dp[dp.length - 1] >= k;
  }
}
