import java.util.SortedMap;
import java.util.TreeMap;

class StockPrice {
  SortedMap<Integer, Integer> timestampToPrice = new TreeMap<>();
  SortedMap<Integer, Integer> priceToCount = new TreeMap<>();

  public void update(int timestamp, int price) {
    if (timestampToPrice.containsKey(timestamp)) {
      int oldPrice = timestampToPrice.get(timestamp);
      priceToCount.put(oldPrice, priceToCount.get(oldPrice) - 1);
      priceToCount.remove(oldPrice, 0);
    }

    timestampToPrice.put(timestamp, price);
    priceToCount.put(price, priceToCount.getOrDefault(price, 0) + 1);
  }

  public int current() {
    return timestampToPrice.get(timestampToPrice.lastKey());
  }

  public int maximum() {
    return priceToCount.lastKey();
  }

  public int minimum() {
    return priceToCount.firstKey();
  }
}

// Your StockPrice object will be instantiated and called as such:
// StockPrice obj = new StockPrice();
// obj.update(timestamp,price);
// int param_2 = obj.current();
// int param_3 = obj.maximum();
// int param_4 = obj.minimum();
