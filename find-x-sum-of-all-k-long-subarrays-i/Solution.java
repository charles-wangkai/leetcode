import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int[] findXSum(int[] nums, int k, int x) {
    return IntStream.range(0, nums.length - k + 1)
        .map(i -> computeXSum(IntStream.range(i, i + k).map(j -> nums[j]).toArray(), x))
        .toArray();
  }

  int computeXSum(int[] values, int x) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : values) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    return valueToCount.keySet().stream()
        .sorted(
            Comparator.<Integer, Integer>comparing(valueToCount::get)
                .reversed()
                .thenComparing(Comparator.<Integer, Integer>comparing(value -> value).reversed()))
        .limit(x)
        .mapToInt(value -> value * valueToCount.get(value))
        .sum();
  }
}