import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int[] intersect(int[] nums1, int[] nums2) {
    Map<Integer, Integer> numToCount1 = buildNumToCount(nums1);
    Map<Integer, Integer> numToCount2 = buildNumToCount(nums2);

    List<Integer> result = new ArrayList<>();
    for (int num : numToCount1.keySet()) {
      for (int i = Math.min(numToCount1.get(num), numToCount2.getOrDefault(num, 0)); i >= 1; --i) {
        result.add(num);
      }
    }

    return result.stream().mapToInt(x -> x).toArray();
  }

  Map<Integer, Integer> buildNumToCount(int[] nums) {
    Map<Integer, Integer> numToCount = new HashMap<>();
    for (int num : nums) {
      numToCount.put(num, numToCount.getOrDefault(num, 0) + 1);
    }

    return numToCount;
  }
}
