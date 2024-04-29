import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minimumAddedInteger(int[] nums1, int[] nums2) {
    int result = Integer.MAX_VALUE;
    for (int index1 = 0; index1 < nums1.length; ++index1) {
      int index1_ = index1;
      for (int index2 = index1 + 1; index2 < nums1.length; ++index2) {
        int index2_ = index2;
        result =
            Math.min(
                result,
                computeDelta(
                    IntStream.range(0, nums1.length)
                        .filter(i -> i != index1_ && i != index2_)
                        .map(i -> nums1[i])
                        .toArray(),
                    nums2));
      }
    }

    return result;
  }

  int computeDelta(int[] removed, int[] nums2) {
    int delta = Arrays.stream(nums2).min().getAsInt() - Arrays.stream(removed).min().getAsInt();

    return Arrays.equals(
            Arrays.stream(removed).map(x -> x + delta).sorted().toArray(),
            Arrays.stream(nums2).sorted().toArray())
        ? delta
        : Integer.MAX_VALUE;
  }
}