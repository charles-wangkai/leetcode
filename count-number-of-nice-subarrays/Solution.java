import java.util.stream.IntStream;

class Solution {
  public int numberOfSubarrays(int[] nums, int k) {
    int[] oddIndices = IntStream.range(0, nums.length).filter(i -> nums[i] % 2 == 1).toArray();

    return IntStream.rangeClosed(0, oddIndices.length - k)
        .map(
            i ->
                computeDiff(nums.length, oddIndices, i - 1, i)
                    * computeDiff(nums.length, oddIndices, i + k - 1, i + k))
        .sum();
  }

  int computeDiff(int length, int[] oddIndices, int leftIndex, int rightIndex) {
    return ((rightIndex == oddIndices.length) ? length : oddIndices[rightIndex])
        - ((leftIndex == -1) ? -1 : oddIndices[leftIndex]);
  }
}
