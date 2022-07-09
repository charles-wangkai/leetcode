import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int maxResult(int[] nums, int k) {
    int[] maxScores = new int[nums.length];
    SortedMap<Integer, Integer> valueToCount = new TreeMap<>();
    for (int i = 0; i < maxScores.length; ++i) {
      maxScores[i] = (valueToCount.isEmpty() ? 0 : valueToCount.lastKey()) + nums[i];
      updateMap(valueToCount, maxScores[i], 1);

      if (i >= k) {
        updateMap(valueToCount, maxScores[i - k], -1);
      }
    }

    return maxScores[maxScores.length - 1];
  }

  void updateMap(SortedMap<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}
