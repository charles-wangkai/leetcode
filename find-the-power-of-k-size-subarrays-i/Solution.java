import java.util.stream.IntStream;

class Solution {
  public int[] resultsArray(int[] nums, int k) {
    return IntStream.range(0, nums.length - k + 1)
        .map(
            i ->
                IntStream.range(0, k - 1).allMatch(j -> nums[i + j + 1] - nums[i + j] == 1)
                    ? nums[i + k - 1]
                    : -1)
        .toArray();
  }
}