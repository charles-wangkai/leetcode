import java.util.HashMap;
import java.util.Map;

class Solution {
  public int largestUniqueNumber(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : nums) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    return valueToCount.keySet().stream()
        .filter(value -> valueToCount.get(value) == 1)
        .mapToInt(Integer::intValue)
        .max()
        .orElse(-1);
  }
}
