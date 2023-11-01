import java.util.Arrays;

class Solution {
  public long minSum(int[] nums1, int[] nums2) {
    long minSum1 = Arrays.stream(nums1).map(x -> Math.max(1, x)).asLongStream().sum();
    long minSum2 = Arrays.stream(nums2).map(x -> Math.max(1, x)).asLongStream().sum();
    if (minSum1 > minSum2) {
      return minSum(nums2, nums1);
    }
    if (minSum1 == minSum2) {
      return minSum1;
    }
    if (Arrays.stream(nums1).allMatch(x -> x != 0)) {
      return -1;
    }

    return minSum2;
  }
}
