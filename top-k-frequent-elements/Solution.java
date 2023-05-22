import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : nums) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    int minCount =
        valueToCount.values().stream()
            .sorted(Comparator.reverseOrder())
            .skip(k - 1)
            .findFirst()
            .get();

    return valueToCount.keySet().stream()
        .filter(value -> valueToCount.get(value) >= minCount)
        .mapToInt(Integer::intValue)
        .toArray();
  }
}
