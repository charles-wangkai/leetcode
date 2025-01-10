import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public long maximumCoins(int[][] coins, int k) {
    return Math.max(
        computeMaxCoinNum(coins, k),
        computeMaxCoinNum(
            Arrays.stream(coins)
                .map(coin -> new int[] {-coin[1], -coin[0], coin[2]})
                .toArray(int[][]::new),
            k));
  }

  public long computeMaxCoinNum(int[][] coins, int k) {
    Arrays.sort(coins, Comparator.comparing(coin -> coin[0]));

    long result = 0;
    long sum = 0;
    int endIndex = -1;
    for (int beginIndex = 0; beginIndex < coins.length; ++beginIndex) {
      while (endIndex != coins.length - 1
          && coins[endIndex + 1][1] - coins[beginIndex][0] + 1 <= k) {
        ++endIndex;
        sum += (coins[endIndex][1] - coins[endIndex][0] + 1L) * coins[endIndex][2];
      }

      if (endIndex == beginIndex - 1) {
        result = Math.max(result, (long) coins[beginIndex][2] * k);
      } else {
        result = Math.max(result, sum);

        int extra = k - (coins[endIndex][1] - coins[beginIndex][0] + 1);
        if (endIndex != coins.length - 1) {
          result =
              Math.max(
                  result,
                  sum
                      + (long) coins[endIndex + 1][2]
                          * Math.max(0, extra - (coins[endIndex + 1][0] - coins[endIndex][1] - 1)));
        }
      }

      sum -= (coins[beginIndex][1] - coins[beginIndex][0] + 1L) * coins[beginIndex][2];
    }

    return result;
  }
}