import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public double minPrice(int[] prices, int[] discounts) {
    prices =
        Arrays.stream(prices)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();
    discounts =
        Arrays.stream(discounts)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();

    int[] prices_ = prices;
    int[] discounts_ = discounts;
    return IntStream.range(0, prices.length)
        .mapToDouble(
            i -> prices_[i] * (100 - ((i < discounts_.length) ? discounts_[i] : 0)) / 100.0)
        .sum();
  }
}