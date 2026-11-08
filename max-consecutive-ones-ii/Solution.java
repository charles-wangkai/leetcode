import java.util.stream.IntStream;

class Solution {
  public int findMaxConsecutiveOnes(int[] nums) {
    int[] leftZeroCounts = new int[nums.length];
    for (int i = 0; i < leftZeroCounts.length; ++i) {
      leftZeroCounts[i] = (nums[i] == 1) ? (1 + ((i == 0) ? 0 : leftZeroCounts[i - 1])) : 0;
    }

    int[] rightZeroCounts = new int[nums.length];
    for (int i = rightZeroCounts.length - 1; i >= 0; --i) {
      rightZeroCounts[i] =
          (nums[i] == 1)
              ? (1 + ((i == rightZeroCounts.length - 1) ? 0 : rightZeroCounts[i + 1]))
              : 0;
    }

    return IntStream.range(0, nums.length)
        .filter(i -> nums[i] == 0)
        .map(i ->
            ((i == 0) ? 0 : leftZeroCounts[i - 1])
                + 1
                + ((i == nums.length - 1) ? 0 : rightZeroCounts[i + 1]))
        .max()
        .orElse(nums.length);
  }
}