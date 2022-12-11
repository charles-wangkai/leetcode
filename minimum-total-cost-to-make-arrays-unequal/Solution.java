// https://leetcode.com/problems/minimum-total-cost-to-make-arrays-unequal/solutions/2897887/simple-solution-with-diagram-and-intuition-c-o-n-time-and-space/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public long minimumTotalCost(int[] nums1, int[] nums2) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int i = 0; i < nums1.length; ++i) {
      if (nums1[i] == nums2[i]) {
        valueToCount.put(nums1[i], valueToCount.getOrDefault(nums1[i], 0) + 1);
      }
    }
    if (valueToCount.isEmpty()) {
      return 0;
    }

    int maxCount = valueToCount.values().stream().mapToInt(x -> x).max().getAsInt();
    int valueWithMaxCount =
        valueToCount.keySet().stream()
            .filter(value -> valueToCount.get(value) == maxCount)
            .findAny()
            .get();

    int extraNeeded =
        Math.max(
            0,
            maxCount
                - ((int) IntStream.range(0, nums1.length).filter(i -> nums1[i] == nums2[i]).count()
                    - maxCount));
    int[] extraIndices =
        IntStream.range(0, nums1.length)
            .filter(
                i ->
                    nums1[i] != nums2[i]
                        && nums1[i] != valueWithMaxCount
                        && nums2[i] != valueWithMaxCount)
            .toArray();

    return (extraIndices.length < extraNeeded)
        ? -1
        : (IntStream.range(0, nums1.length).filter(i -> nums1[i] == nums2[i]).asLongStream().sum()
            + Arrays.stream(extraIndices).limit(extraNeeded).asLongStream().sum());
  }
}
