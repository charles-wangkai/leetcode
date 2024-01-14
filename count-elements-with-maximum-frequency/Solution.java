import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maxFrequencyElements(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    int maxCount = valueToCount.values().stream().mapToInt(Integer::intValue).max().getAsInt();

    return valueToCount.values().stream()
        .filter(count -> count == maxCount)
        .mapToInt(Integer::intValue)
        .sum();
  }
}