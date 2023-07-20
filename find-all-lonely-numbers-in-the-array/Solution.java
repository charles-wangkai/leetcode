import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public List<Integer> findLonely(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    return valueToCount.keySet().stream()
        .filter(
            value ->
                valueToCount.get(value) == 1
                    && !valueToCount.containsKey(value - 1)
                    && !valueToCount.containsKey(value + 1))
        .toList();
  }
}
