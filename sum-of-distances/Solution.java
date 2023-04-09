import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public long[] distance(int[] nums) {
    Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      valueToIndices.putIfAbsent(nums[i], new ArrayList<>());
      valueToIndices.get(nums[i]).add(i);
    }

    Map<Integer, Long> valueToIndexTotal =
        valueToIndices.keySet().stream()
            .collect(
                Collectors.toMap(
                    value -> value,
                    value ->
                        valueToIndices.get(value).stream()
                            .mapToInt(Integer::intValue)
                            .asLongStream()
                            .sum()));
    Map<Integer, Integer> valueToLeftCount =
        valueToIndices.keySet().stream().collect(Collectors.toMap(value -> value, value -> 0));
    Map<Integer, Long> valueToLeftIndexSum =
        valueToIndices.keySet().stream().collect(Collectors.toMap(value -> value, value -> 0L));

    long[] result = new long[nums.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] =
          ((long) i * valueToLeftCount.get(nums[i]) - valueToLeftIndexSum.get(nums[i]))
              + (valueToIndexTotal.get(nums[i])
                  - valueToLeftIndexSum.get(nums[i])
                  - (long) i
                      * (valueToIndices.get(nums[i]).size() - valueToLeftCount.get(nums[i])));

      valueToLeftCount.put(nums[i], valueToLeftCount.get(nums[i]) + 1);
      valueToLeftIndexSum.put(nums[i], valueToLeftIndexSum.get(nums[i]) + i);
    }

    return result;
  }
}
