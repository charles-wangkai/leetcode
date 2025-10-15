import java.util.stream.IntStream;

class Solution {
  public int[] minSubarraySort(int[] nums, int k) {
    return IntStream.rangeClosed(0, nums.length - k)
        .map(
            beginIndex -> {
              int[] sorted =
                  IntStream.range(beginIndex, beginIndex + k).map(i -> nums[i]).sorted().toArray();
              int[] diffIndices =
                  IntStream.range(0, sorted.length)
                      .filter(i -> sorted[i] != nums[beginIndex + i])
                      .toArray();

              return (diffIndices.length == 0)
                  ? 0
                  : (diffIndices[diffIndices.length - 1] - diffIndices[0] + 1);
            })
        .toArray();
  }
}