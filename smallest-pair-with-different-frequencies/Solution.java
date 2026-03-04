import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] minDistinctFreqPair(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    int[] sortedValues =
        valueToCount.keySet().stream().sorted().mapToInt(Integer::intValue).toArray();

    for (int x : sortedValues) {
      for (int y : sortedValues) {
        if (y > x && !valueToCount.get(y).equals(valueToCount.get(x))) {
          return new int[] {x, y};
        }
      }
    }

    return new int[] {-1, -1};
  }
}