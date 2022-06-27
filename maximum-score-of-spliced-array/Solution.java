import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maximumsSplicedArray(int[] nums1, int[] nums2) {
    return Math.max(
        Arrays.stream(nums1).sum()
            + computeMaxRangeSum(
                IntStream.range(0, nums1.length).map(i -> nums2[i] - nums1[i]).toArray()),
        Arrays.stream(nums2).sum()
            + computeMaxRangeSum(
                IntStream.range(0, nums2.length).map(i -> nums1[i] - nums2[i]).toArray()));
  }

  int computeMaxRangeSum(int[] a) {
    int result = 0;
    int sum = 0;
    for (int ai : a) {
      sum = Math.max(0, sum + ai);
      result = Math.max(result, sum);
    }

    return result;
  }
}