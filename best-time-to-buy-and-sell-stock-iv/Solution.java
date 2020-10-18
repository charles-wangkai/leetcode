import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maxProfit(int k, int[] prices) {
    if (k >= prices.length - 1) {
      return maxProfitWithUnlimitedTransactions(prices);
    }

    int[] inProfits = new int[k + 1];
    int[] offProfits = new int[k + 1];
    for (int i = 0; i < prices.length - 1; ++i) {
      int priceDelta = prices[i + 1] - prices[i];
      int[] nextInProfits = new int[inProfits.length];
      int[] nextOffProfits = new int[offProfits.length];
      for (int j = 1; j < nextInProfits.length; ++j) {
        nextInProfits[j] = Math.max(inProfits[j], offProfits[j - 1]) + priceDelta;
        nextOffProfits[j] = Math.max(inProfits[j], offProfits[j]);
      }

      inProfits = nextInProfits;
      offProfits = nextOffProfits;
    }

    return IntStream.concat(Arrays.stream(inProfits), Arrays.stream(offProfits)).max().orElse(0);
  }

  int maxProfitWithUnlimitedTransactions(int[] prices) {
    return IntStream.range(0, prices.length - 1)
        .map(i -> Math.max(0, prices[i + 1] - prices[i]))
        .sum();
  }
}
