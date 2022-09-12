import java.util.HashMap;
import java.util.Map;

class Solution {
  public int mostFrequentEven(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : nums) {
      if (value % 2 == 0) {
        valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
      }
    }
    if (valueToCount.isEmpty()) {
      return -1;
    }

    int maxCount = valueToCount.values().stream().mapToInt(x -> x).max().getAsInt();

    return valueToCount.keySet().stream()
        .filter(value -> valueToCount.get(value) == maxCount)
        .mapToInt(x -> x)
        .min()
        .getAsInt();
  }
}