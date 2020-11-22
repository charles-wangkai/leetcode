import java.util.stream.IntStream;

class Solution {
  public int waysToMakeFair(int[] nums) {
    int leftDiff = 0;
    int[] leftDiffs = new int[nums.length];
    for (int i = 0; i < leftDiffs.length; ++i) {
      leftDiffs[i] = leftDiff;
      leftDiff = nums[i] - leftDiff;
    }

    int rightDiff = 0;
    int[] rightDiffs = new int[nums.length];
    for (int i = rightDiffs.length - 1; i >= 0; --i) {
      rightDiffs[i] = rightDiff;
      rightDiff = nums[i] - rightDiff;
    }

    return (int) IntStream.range(0, nums.length).filter(i -> leftDiffs[i] == rightDiffs[i]).count();
  }
}
