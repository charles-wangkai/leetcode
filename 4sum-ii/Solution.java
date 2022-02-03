import java.util.HashMap;
import java.util.Map;

class Solution {
  public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
    Map<Integer, Integer> sumToCount1 = buildSumToCount(nums1, nums2);
    Map<Integer, Integer> sumToCount2 = buildSumToCount(nums3, nums4);

    return sumToCount1.keySet().stream()
        .mapToInt(sum1 -> sumToCount1.get(sum1) * sumToCount2.getOrDefault(-sum1, 0))
        .sum();
  }

  Map<Integer, Integer> buildSumToCount(int[] x, int[] y) {
    Map<Integer, Integer> sumToCount = new HashMap<>();
    for (int oneX : x) {
      for (int oneY : y) {
        int sum = oneX + oneY;

        sumToCount.put(sum, sumToCount.getOrDefault(sum, 0) + 1);
      }
    }

    return sumToCount;
  }
}
