import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int findSmallestInteger(int[] nums, int value) {
    SortedMap<Integer, Integer> valueToCount = new TreeMap<>();
    for (int num : nums) {
      update(valueToCount, Math.floorMod(num, value), 1);
    }

    for (int i = 0; ; ++i) {
      while (!valueToCount.isEmpty() && valueToCount.firstKey() < i) {
        int firstKey = valueToCount.firstKey();
        int count = valueToCount.get(firstKey);

        update(valueToCount, firstKey, -count);
        update(valueToCount, firstKey + value, count);
      }

      if (valueToCount.isEmpty() || valueToCount.firstKey() != i) {
        return i;
      }

      update(valueToCount, valueToCount.firstKey(), -1);
    }
  }

  void update(SortedMap<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}
