import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int[] nums : new int[][] {nums1, nums2, nums3}) {
      for (int num : Arrays.stream(nums).distinct().toArray()) {
        valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
      }
    }

    return valueToCount.keySet().stream().filter(value -> valueToCount.get(value) >= 2).toList();
  }
}
