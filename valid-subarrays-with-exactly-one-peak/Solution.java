import java.util.stream.IntStream;

class Solution {
  public long validSubarrays(int[] nums, int k) {
    int[] peakIndices =
        IntStream.range(1, nums.length - 1)
            .filter(i -> nums[i] > nums[i - 1] && nums[i] > nums[i + 1])
            .toArray();

    return IntStream.range(0, peakIndices.length)
        .mapToLong(
            i ->
                Math.min(k + 1L, peakIndices[i] - ((i == 0) ? -1 : peakIndices[i - 1]))
                    * Math.min(
                        k + 1,
                        ((i == peakIndices.length - 1) ? nums.length : peakIndices[i + 1])
                            - peakIndices[i]))
        .sum();
  }
}