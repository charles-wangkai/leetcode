import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public long numberOfPairs(int[] nums1, int[] nums2, int k) {
    Map<Integer, Integer> valueToCount1 = buildValueToCount(nums1);
    Map<Integer, Integer> valueToCount2 = buildValueToCount(nums2);

    int maxValue1 = Arrays.stream(nums1).max().getAsInt();

    long result = 0;
    for (int value2 : valueToCount2.keySet()) {
      for (int i = value2 * k; i <= maxValue1; i += value2 * k) {
        result += (long) valueToCount2.get(value2) * valueToCount1.getOrDefault(i, 0);
      }
    }

    return result;
  }

  Map<Integer, Integer> buildValueToCount(int[] values) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : values) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    return valueToCount;
  }
}