import java.util.stream.IntStream;

class Solution {
  public int countMatchingSubarrays(int[] nums, int[] pattern) {
    return (int)
        IntStream.range(0, nums.length - pattern.length)
            .filter(
                beginIndex ->
                    IntStream.range(0, pattern.length)
                        .allMatch(
                            i ->
                                Integer.compare(nums[beginIndex + i + 1], nums[beginIndex + i])
                                    == pattern[i]))
            .count();
  }
}