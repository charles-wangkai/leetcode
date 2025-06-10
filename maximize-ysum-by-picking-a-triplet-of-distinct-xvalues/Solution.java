import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maxSumDistinctTriplet(int[] x, int[] y) {
    Map<Integer, Integer> xToMaxY = new HashMap<>();
    for (int i = 0; i < x.length; ++i) {
      xToMaxY.put(x[i], Math.max(xToMaxY.getOrDefault(x[i], Integer.MIN_VALUE), y[i]));
    }

    return (xToMaxY.size() >= 3)
        ? xToMaxY.values().stream()
            .sorted(Comparator.reverseOrder())
            .limit(3)
            .mapToInt(Integer::intValue)
            .sum()
        : -1;
  }
}