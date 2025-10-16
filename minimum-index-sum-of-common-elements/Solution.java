import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int minimumSum(int[] nums1, int[] nums2) {
    Map<Integer, Integer> valueToIndex = new HashMap<>();
    for (int i = 0; i < nums2.length; ++i) {
      if (!valueToIndex.containsKey(nums2[i])) {
        valueToIndex.put(nums2[i], i);
      }
    }

    return IntStream.range(0, nums1.length)
        .filter(i -> valueToIndex.containsKey(nums1[i]))
        .map(i -> i + valueToIndex.get(nums1[i]))
        .min()
        .orElse(-1);
  }
}