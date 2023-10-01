import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minOperations(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    return valueToCount.values().stream().anyMatch(count -> count == 1)
        ? -1
        : valueToCount.values().stream().mapToInt(count -> (count + 2) / 3).sum();
  }
}
