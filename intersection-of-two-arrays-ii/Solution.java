import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int[] intersect(int[] nums1, int[] nums2) {
    Map<Integer, Integer> valueToCount1 = buildValueToCount(nums1);
    Map<Integer, Integer> valueToCount2 = buildValueToCount(nums2);

    List<Integer> result = new ArrayList<>();
    for (int value : valueToCount1.keySet()) {
      for (int i = Math.min(valueToCount1.get(value), valueToCount2.getOrDefault(value, 0));
          i >= 1;
          --i) {
        result.add(value);
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  Map<Integer, Integer> buildValueToCount(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    return valueToCount;
  }
}
