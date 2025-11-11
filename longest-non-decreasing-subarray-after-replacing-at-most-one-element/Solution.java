import java.util.stream.IntStream;

class Solution {
  public int longestSubarray(int[] nums) {
    int[] leftLengths = new int[nums.length];
    for (int i = 0; i < leftLengths.length; ++i) {
      leftLengths[i] = (i != 0 && nums[i] >= nums[i - 1]) ? (leftLengths[i - 1] + 1) : 1;
    }

    int[] rightLengths = new int[nums.length];
    for (int i = rightLengths.length - 1; i >= 0; --i) {
      rightLengths[i] =
          (i != rightLengths.length - 1 && nums[i] <= nums[i + 1]) ? (rightLengths[i + 1] + 1) : 1;
    }

    return Math.max(
        Math.max(
                IntStream.range(0, leftLengths.length - 1).map(i -> leftLengths[i]).max().orElse(0),
                IntStream.range(1, rightLengths.length).map(i -> rightLengths[i]).max().orElse(0))
            + 1,
        IntStream.rangeClosed(1, nums.length - 2)
            .filter(i -> nums[i - 1] <= nums[i + 1])
            .map(i -> leftLengths[i - 1] + rightLengths[i + 1] + 1)
            .max()
            .orElse(0));
  }
}