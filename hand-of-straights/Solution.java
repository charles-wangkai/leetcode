import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public boolean isNStraightHand(int[] hand, int groupSize) {
    if (hand.length % groupSize != 0) {
      return false;
    }

    SortedMap<Integer, Integer> valueToCount = new TreeMap<>();
    for (int value : hand) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    while (!valueToCount.isEmpty()) {
      int minValue = valueToCount.firstKey();
      for (int value = minValue; value < minValue + groupSize; ++value) {
        if (!valueToCount.containsKey(value)) {
          return false;
        }

        valueToCount.put(value, valueToCount.get(value) - 1);
        valueToCount.remove(value, 0);
      }
    }

    return true;
  }
}
