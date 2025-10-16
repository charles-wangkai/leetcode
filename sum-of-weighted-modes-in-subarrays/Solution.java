import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

class Solution {
  public long modeWeight(int[] nums, int k) {
    long result = 0;
    Map<Integer, Integer> valueToCount = new HashMap<>();
    SortedMap<Integer, SortedSet<Integer>> countToValues = new TreeMap<>();
    for (int i = 0; i < nums.length; ++i) {
      update(valueToCount, countToValues, nums[i], 1);

      if (i >= k - 1) {
        int maxCount = countToValues.lastKey();
        result += (long) maxCount * countToValues.get(maxCount).first();

        update(valueToCount, countToValues, nums[i - k + 1], -1);
      }
    }

    return result;
  }

  void update(
      Map<Integer, Integer> valueToCount,
      SortedMap<Integer, SortedSet<Integer>> countToValues,
      int value,
      int delta) {
    int oldCount = valueToCount.getOrDefault(value, 0);
    int newCount = oldCount + delta;

    valueToCount.put(value, newCount);

    if (oldCount != 0) {
      countToValues.get(oldCount).remove(value);
      if (countToValues.get(oldCount).isEmpty()) {
        countToValues.remove(oldCount);
      }
    }
    if (newCount != 0) {
      countToValues.putIfAbsent(newCount, new TreeSet<>());
      countToValues.get(newCount).add(value);
    }
  }
}