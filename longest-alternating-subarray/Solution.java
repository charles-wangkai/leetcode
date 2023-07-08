import java.util.stream.IntStream;

class Solution {
  public int alternatingSubarray(int[] nums) {
    for (int length = nums.length; length >= 2; --length) {
      for (int beginIndex = 0; beginIndex + length <= nums.length; ++beginIndex) {
        if (isAlternating(nums, beginIndex, length)) {
          return length;
        }
      }
    }

    return -1;
  }

  boolean isAlternating(int[] nums, int beginIndex, int length) {
    return IntStream.range(0, length - 1)
        .allMatch(i -> nums[beginIndex + i + 1] - nums[beginIndex + i] == ((i % 2 == 0) ? 1 : -1));
  }
}
