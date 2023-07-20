import java.util.HashMap;
import java.util.Map;

class Solution {
  public int findLucky(int[] arr) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : arr) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    return valueToCount.keySet().stream()
        .mapToInt(Integer::intValue)
        .filter(value -> value == valueToCount.get(value))
        .max()
        .orElse(-1);
  }
}
