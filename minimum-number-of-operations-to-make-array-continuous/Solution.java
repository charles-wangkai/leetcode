import java.util.Arrays;

class Solution {
  public int minOperations(int[] nums) {
    int[] sorted = Arrays.stream(nums).boxed().sorted().distinct().mapToInt(x -> x).toArray();

    int result = nums.length;
    int endIndex = 0;
    for (int beginIndex = 0; beginIndex < sorted.length; ++beginIndex) {
      while (endIndex + 1 != sorted.length
          && sorted[endIndex + 1] - sorted[beginIndex] + 1 <= nums.length) {
        ++endIndex;
      }

      result = Math.min(result, nums.length - (endIndex - beginIndex + 1));
    }

    return result;
  }
}
