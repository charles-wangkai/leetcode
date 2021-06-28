import java.util.stream.IntStream;

class Solution {
  public boolean canBeIncreasing(int[] nums) {
    int n = nums.length;

    boolean[] leftIncreasings = new boolean[n];
    for (int i = 0; i < leftIncreasings.length; ++i) {
      leftIncreasings[i] = i == 0 || (nums[i] > nums[i - 1] && leftIncreasings[i - 1]);
    }

    boolean[] rightIncreasings = new boolean[n];
    for (int i = rightIncreasings.length - 1; i >= 0; --i) {
      rightIncreasings[i] =
          i == rightIncreasings.length - 1 || (nums[i] < nums[i + 1] && rightIncreasings[i + 1]);
    }

    return IntStream.range(0, n)
        .anyMatch(
            i ->
                (i == 0 || leftIncreasings[i - 1])
                    && (i == rightIncreasings.length - 1 || rightIncreasings[i + 1])
                    && (i == 0 || i == rightIncreasings.length - 1 || nums[i - 1] < nums[i + 1]));
  }
}
