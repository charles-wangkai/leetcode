import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public boolean canReorderDoubled(int[] arr) {
    List<Integer> negatives = new ArrayList<>();
    List<Integer> positives = new ArrayList<>();
    List<Integer> zeros = new ArrayList<>();
    for (int value : arr) {
      if (value < 0) {
        negatives.add(value);
      } else if (value > 0) {
        positives.add(value);
      } else {
        zeros.add(value);
      }
    }

    return isPossible(negatives) && isPossible(positives) && isPossible(zeros);
  }

  boolean isPossible(List<Integer> values) {
    SortedMap<Integer, Integer> absValueToCount = new TreeMap<>();
    for (int value : values) {
      int absValue = Math.abs(value);

      absValueToCount.put(absValue, absValueToCount.getOrDefault(absValue, 0) + 1);
    }

    while (!absValueToCount.isEmpty()) {
      int lower = absValueToCount.firstKey();
      decrement(absValueToCount, lower);

      int upper = lower * 2;
      if (!absValueToCount.containsKey(upper)) {
        return false;
      }
      decrement(absValueToCount, upper);
    }

    return true;
  }

  void decrement(SortedMap<Integer, Integer> absValueToCount, int absValue) {
    absValueToCount.put(absValue, absValueToCount.get(absValue) - 1);
    absValueToCount.remove(absValue, 0);
  }
}
