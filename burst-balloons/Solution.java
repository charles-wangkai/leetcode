import java.util.stream.IntStream;

class Solution {
  public int maxCoins(int[] nums) {
    int[] a =
        IntStream.range(0, nums.length + 2)
            .map(i -> (i == 0 || i == nums.length + 1) ? 1 : nums[i - 1])
            .toArray();

    int n = a.length;

    int[][] dp = new int[n][n];
    for (int length = 3; length <= n; ++length) {
      for (int beginIndex = 0; beginIndex + length - 1 < n; ++beginIndex) {
        int endIndex = beginIndex + length - 1;

        for (int middleIndex = beginIndex + 1; middleIndex < endIndex; ++middleIndex) {
          dp[beginIndex][endIndex] =
              Math.max(
                  dp[beginIndex][endIndex],
                  dp[beginIndex][middleIndex]
                      + dp[middleIndex][endIndex]
                      + a[beginIndex] * a[middleIndex] * a[endIndex]);
        }
      }
    }

    return dp[0][n - 1];
  }
}
