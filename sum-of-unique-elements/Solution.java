import java.util.HashMap;
import java.util.Map;

class Solution {
  public int sumOfUnique(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    return valueToCount.keySet().stream()
        .filter(value -> valueToCount.get(value) == 1)
        .mapToInt(x -> x)
        .sum();
  }
}
