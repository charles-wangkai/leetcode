import java.util.HashMap;
import java.util.Map;

class Solution {
  public int sumDivisibleByK(int[] nums, int k) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    return valueToCount.keySet().stream()
        .filter(value -> valueToCount.get(value) % k == 0)
        .mapToInt(value -> value * valueToCount.get(value))
        .sum();
  }
}