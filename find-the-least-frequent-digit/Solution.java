import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int getLeastFrequentDigit(int n) {
    Map<Integer, Integer> digitToCount = new HashMap<>();
    while (n != 0) {
      int digit = n % 10;
      digitToCount.put(digit, digitToCount.getOrDefault(digit, 0) + 1);

      n /= 10;
    }

    return digitToCount.keySet().stream()
        .min(Comparator.<Integer, Integer>comparing(digitToCount::get).thenComparing(x -> x))
        .get();
  }
}