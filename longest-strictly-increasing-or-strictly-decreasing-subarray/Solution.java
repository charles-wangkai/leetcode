import java.util.stream.IntStream;

class Solution {
  public int longestMonotonicSubarray(int[] nums) {
    int result = 0;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      for (int endIndex = beginIndex; endIndex < nums.length; ++endIndex) {
        if (IntStream.range(beginIndex, endIndex).allMatch(i -> nums[i] < nums[i + 1])
            || IntStream.range(beginIndex, endIndex).allMatch(i -> nums[i] > nums[i + 1])) {
          result = Math.max(result, endIndex - beginIndex + 1);
        }
      }
    }

    return result;
  }
}
