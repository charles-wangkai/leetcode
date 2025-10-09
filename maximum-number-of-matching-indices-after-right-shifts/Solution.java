import java.util.stream.IntStream;

class Solution {
  public int maximumMatchingIndices(int[] nums1, int[] nums2) {
    return IntStream.range(0, nums1.length)
        .map(
            offset ->
                (int)
                    IntStream.range(0, nums1.length)
                        .filter(i -> nums1[(i + offset) % nums1.length] == nums2[i])
                        .count())
        .max()
        .getAsInt();
  }
}