import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> findCoins(int[] numWays) {
    int[] dp = new int[numWays.length + 1];
    dp[0] = 1;

    List<Integer> result = new ArrayList<>();
    for (int d = 1; d <= numWays.length; ++d) {
      if (dp[d] != numWays[d - 1]) {
        result.add(d);

        for (int i = dp.length - 1; i >= 0; --i) {
          for (int j = i + d; j < dp.length; j += d) {
            dp[j] += dp[i];
          }
        }
      }
    }

    return IntStream.range(0, numWays.length).allMatch(i -> numWays[i] == dp[i + 1])
        ? result
        : List.of();
  }
}