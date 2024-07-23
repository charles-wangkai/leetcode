import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] frequencySort(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    return Arrays.stream(nums)
        .boxed()
        .sorted(
            Comparator.<Integer, Integer>comparing(valueToCount::get)
                .thenComparing(Comparator.reverseOrder()))
        .mapToInt(Integer::intValue)
        .toArray();
  }
}
