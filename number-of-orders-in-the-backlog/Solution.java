import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;

class Solution {
  public int getNumberOfBacklogOrders(int[][] orders) {
    SortedMap<Integer, Long> buyPriceToCount = new TreeMap<>();
    SortedMap<Integer, Long> sellPriceToCount = new TreeMap<>();

    for (int[] order : orders) {
      int amount = order[1];
      while (amount != 0) {
        if (order[2] == 0) {
          if (!sellPriceToCount.isEmpty() && sellPriceToCount.firstKey() <= order[0]) {
            int price = sellPriceToCount.firstKey();
            int count = (int) Math.min(amount, sellPriceToCount.get(price));
            amount -= count;
            sellPriceToCount.put(price, sellPriceToCount.get(price) - count);
            sellPriceToCount.remove(price, 0L);
          } else {
            buyPriceToCount.put(order[0], buyPriceToCount.getOrDefault(order[0], 0L) + amount);

            break;
          }
        } else {
          if (!buyPriceToCount.isEmpty() && buyPriceToCount.lastKey() >= order[0]) {
            int price = buyPriceToCount.lastKey();
            int count = (int) Math.min(amount, buyPriceToCount.get(price));
            amount -= count;
            buyPriceToCount.put(price, buyPriceToCount.get(price) - count);
            buyPriceToCount.remove(price, 0L);
          } else {
            sellPriceToCount.put(order[0], sellPriceToCount.getOrDefault(order[0], 0L) + amount);

            break;
          }
        }
      }
    }

    return (int)
        (Stream.concat(buyPriceToCount.values().stream(), sellPriceToCount.values().stream())
                .mapToLong(x -> x)
                .sum()
            % 1_000_000_007);
  }
}
