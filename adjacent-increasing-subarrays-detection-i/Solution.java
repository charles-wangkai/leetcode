import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
    return IntStream.rangeClosed(0, nums.size() - 2 * k)
        .anyMatch(i -> isIncreasing(nums, k, i) && isIncreasing(nums, k, i + k));
  }

  boolean isIncreasing(List<Integer> nums, int k, int beginIndex) {
    return IntStream.range(0, k - 1)
        .allMatch(i -> nums.get(beginIndex + i) < nums.get(beginIndex + i + 1));
  }
}