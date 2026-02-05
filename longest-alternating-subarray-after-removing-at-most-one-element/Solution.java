import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int longestAlternating(int[] nums) {
    int[] leftLengths = buildLeftLengths(nums);
    int[] rightLengths = buildRightLengths(nums);

    return Math.max(
        Arrays.stream(leftLengths).max().getAsInt(),
        IntStream.rangeClosed(1, nums.length - 2)
            .filter(i -> nums[i - 1] != nums[i + 1])
            .map(
                i ->
                    extend(nums, leftLengths, i + 1, i - 1, i - 2)
                        + extend(nums, rightLengths, i - 1, i + 1, i + 2))
            .max()
            .orElse(-1));
  }

  int[] buildLeftLengths(int[] nums) {
    int[] result = new int[nums.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] =
          1 + ((i == 0 || nums[i] == nums[i - 1]) ? 0 : extend(nums, result, i, i - 1, i - 2));
    }

    return result;
  }

  int[] buildRightLengths(int[] nums) {
    int[] result = new int[nums.length];
    for (int i = result.length - 1; i >= 0; --i) {
      result[i] =
          1
              + ((i == nums.length - 1 || nums[i] == nums[i + 1])
                  ? 0
                  : extend(nums, result, i, i + 1, i + 2));
    }

    return result;
  }

  int extend(int[] nums, int[] lengths, int prevIndex, int currIndex, int nextIndex) {
    return (lengths[currIndex] == 1
            || (nums[prevIndex] < nums[currIndex]) == (nums[currIndex] < nums[nextIndex]))
        ? 1
        : lengths[currIndex];
  }
}