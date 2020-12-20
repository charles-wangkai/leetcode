import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int maxResult(int[] nums, int k) {
    int[] maxScores = new int[nums.length];
    SortedMap<Integer, Integer> valueToCount = new TreeMap<>();
    for (int i = 0; i < maxScores.length; ++i) {
      maxScores[i] = (valueToCount.isEmpty() ? 0 : valueToCount.lastKey()) + nums[i];
      valueToCount.put(maxScores[i], valueToCount.getOrDefault(maxScores[i], 0) + 1);
      if (i - k >= 0) {
        valueToCount.put(maxScores[i - k], valueToCount.get(maxScores[i - k]) - 1);
        valueToCount.remove(maxScores[i - k], 0);
      }
    }

    return maxScores[maxScores.length - 1];
  }
}
