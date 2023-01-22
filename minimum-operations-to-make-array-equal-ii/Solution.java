import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long minOperations(int[] nums1, int[] nums2, int k) {
    if (k == 0) {
      return Arrays.equals(nums1, nums2) ? 0 : -1;
    }

    return (Arrays.stream(nums1).asLongStream().sum() == Arrays.stream(nums2).asLongStream().sum()
            && IntStream.range(0, nums1.length).allMatch(i -> (nums2[i] - nums1[i]) % k == 0))
        ? (IntStream.range(0, nums1.length)
                .map(i -> Math.max(0, nums2[i] - nums1[i]))
                .asLongStream()
                .sum()
            / k)
        : -1;
  }
}
