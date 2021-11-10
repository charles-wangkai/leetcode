import java.util.stream.IntStream;

class Solution {
  public int maxProfit(int[] prices) {
    return IntStream.range(0, prices.length - 1)
        .map(i -> Math.max(0, prices[i + 1] - prices[i]))
        .sum();
  }
}
