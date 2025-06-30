import java.util.HashMap;
import java.util.Map;

class Solution {
  public int findLHS(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    return valueToCount.keySet().stream()
        .filter(value -> valueToCount.containsKey(value + 1))
        .mapToInt(value -> valueToCount.get(value) + valueToCount.get(value + 1))
        .max()
        .orElse(0);
  }
}
