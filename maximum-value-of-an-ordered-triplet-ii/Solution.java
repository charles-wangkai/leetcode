import java.util.stream.IntStream;

class Solution {
  public long maximumTripletValue(int[] nums) {
    int[] leftMaxs = new int[nums.length];
    for (int i = 0; i < leftMaxs.length; ++i) {
      leftMaxs[i] = Math.max((i == 0) ? Integer.MIN_VALUE : leftMaxs[i - 1], nums[i]);
    }

    int[] rightMaxs = new int[nums.length];
    for (int i = rightMaxs.length - 1; i >= 0; --i) {
      rightMaxs[i] =
          Math.max((i == rightMaxs.length - 1) ? Integer.MIN_VALUE : rightMaxs[i + 1], nums[i]);
    }

    return Math.max(
        0,
        IntStream.rangeClosed(1, nums.length - 2)
            .mapToLong(i -> (long) (leftMaxs[i - 1] - nums[i]) * rightMaxs[i + 1])
            .max()
            .getAsLong());
  }
}
