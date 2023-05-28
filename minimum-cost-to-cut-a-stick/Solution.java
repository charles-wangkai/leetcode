import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minCost(int n, int[] cuts) {
    int[] sorted = IntStream.concat(IntStream.of(0, n), Arrays.stream(cuts)).sorted().toArray();

    int[][] dp = new int[sorted.length][sorted.length];
    for (int length = 2; length <= sorted.length - 1; ++length) {
      for (int beginIndex = 0; beginIndex + length < sorted.length; ++beginIndex) {
        int endIndex = beginIndex + length;

        int beginIndex_ = beginIndex;
        dp[beginIndex][endIndex] =
            sorted[endIndex]
                - sorted[beginIndex]
                + IntStream.range(beginIndex + 1, endIndex)
                    .map(i -> dp[beginIndex_][i] + dp[i][endIndex])
                    .min()
                    .getAsInt();
      }
    }

    return dp[0][sorted.length - 1];
  }
}
