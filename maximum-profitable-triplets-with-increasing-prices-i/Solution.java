import java.util.OptionalInt;
import java.util.stream.IntStream;

class Solution {
  public int maxProfit(int[] prices, int[] profits) {
    int result = -1;
    for (int j = 0; j < prices.length; ++j) {
      int j_ = j;
      OptionalInt leftMaxProfit =
          IntStream.range(0, j).filter(i -> prices[i] < prices[j_]).map(i -> profits[i]).max();
      OptionalInt rightMaxProfit =
          IntStream.range(j + 1, prices.length)
              .filter(k -> prices[k] > prices[j_])
              .map(k -> profits[k])
              .max();
      if (leftMaxProfit.isPresent() && rightMaxProfit.isPresent()) {
        result =
            Math.max(result, leftMaxProfit.getAsInt() + profits[j] + rightMaxProfit.getAsInt());
      }
    }

    return result;
  }
}
