import java.util.Arrays;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

class Solution {
  public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
    int[] absDiffs =
        IntStream.range(0, nums1.length).map(i -> Math.abs(nums1[i] - nums2[i])).toArray();

    long total = Arrays.stream(absDiffs).asLongStream().sum();
    long minAbsDiffSum = total;

    NavigableMap<Integer, Integer> valueToCount = new TreeMap<>();
    for (int value : nums1) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    for (int i = 0; i < nums1.length; ++i) {
      valueToCount.put(nums1[i], valueToCount.get(nums1[i]) - 1);
      valueToCount.remove(nums1[i], 0);

      Integer floor = valueToCount.floorKey(nums2[i]);
      if (floor != null) {
        minAbsDiffSum = Math.min(minAbsDiffSum, total - absDiffs[i] + nums2[i] - floor);
      }
      Integer ceiling = valueToCount.ceilingKey(nums2[i]);
      if (ceiling != null) {
        minAbsDiffSum = Math.min(minAbsDiffSum, total - absDiffs[i] + ceiling - nums2[i]);
      }

      valueToCount.put(nums1[i], valueToCount.getOrDefault(nums1[i], 0) + 1);
    }

    return (int) (minAbsDiffSum % 1_000_000_007);
  }
}
