import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maxSelectedElements(int[] nums) {
    Arrays.sort(nums);

    int[] values =
        IntStream.range(0, nums.length)
            .filter(i -> i <= 1 || nums[i] != nums[i - 2])
            .map(i -> nums[i])
            .toArray();

    int result = 0;
    int[][] dp = new int[values.length][2];
    for (int i = 0; i < dp.length; ++i) {
      for (int j = 0; j < 2; ++j) {
        dp[i][j] = 1;
        for (int prevI = Math.max(0, i - 2); prevI <= i - 1; ++prevI) {
          for (int prevJ = 0; prevJ <= 1; ++prevJ) {
            if (values[prevI] + prevJ + 1 == values[i] + j) {
              dp[i][j] = Math.max(dp[i][j], dp[prevI][prevJ] + 1);
            }
          }
        }

        result = Math.max(result, dp[i][j]);
      }
    }

    return result;
  }
}