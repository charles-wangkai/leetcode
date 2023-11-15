import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minArrayLength(int[] nums, int k) {
    if (Arrays.stream(nums).anyMatch(x -> x == 0)) {
      return 1;
    }
    if (IntStream.range(0, nums.length - 1).anyMatch(i -> nums[i] == 1 && nums[i + 1] == 1)) {
      return minArrayLength(
          IntStream.range(0, nums.length)
              .filter(i -> nums[i] != 1 || i == 0 || nums[i - 1] != 1)
              .map(i -> nums[i])
              .toArray(),
          k);
    }

    int[] dp = new int[nums.length + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (int i = 1; i < dp.length; ++i) {
      long product = 1;
      for (int j = i - 1; j >= 0; --j) {
        product *= nums[j];
        if (j != i - 1 && product > k) {
          break;
        }

        dp[i] = Math.min(dp[i], dp[j] + 1);
      }
    }

    return dp[dp.length - 1];
  }
}
