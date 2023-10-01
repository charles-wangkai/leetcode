import java.util.stream.IntStream;

class Solution {
  public long maximumTripletValue(int[] nums) {
    int[] leftMins = new int[nums.length];
    for (int i = 0; i < leftMins.length; ++i) {
      leftMins[i] = Math.min((i == 0) ? Integer.MAX_VALUE : leftMins[i - 1], nums[i]);
    }

    int[] leftMaxs = new int[nums.length];
    for (int i = 0; i < leftMaxs.length; ++i) {
      leftMaxs[i] = Math.max((i == 0) ? -1 : leftMaxs[i - 1], nums[i]);
    }

    int[] rightMins = new int[nums.length];
    for (int i = rightMins.length - 1; i >= 0; --i) {
      rightMins[i] =
          Math.min((i == rightMins.length - 1) ? Integer.MAX_VALUE : rightMins[i + 1], nums[i]);
    }

    int[] rightMaxs = new int[nums.length];
    for (int i = rightMaxs.length - 1; i >= 0; --i) {
      rightMaxs[i] = Math.max((i == rightMaxs.length - 1) ? -1 : rightMaxs[i + 1], nums[i]);
    }

    return Math.max(
        0,
        IntStream.rangeClosed(1, nums.length - 2)
            .mapToLong(
                i ->
                    Math.max(
                        Math.max(
                            (long) (leftMins[i - 1] - nums[i]) * rightMins[i + 1],
                            (long) (leftMaxs[i - 1] - nums[i]) * rightMins[i + 1]),
                        Math.max(
                            (long) (leftMins[i - 1] - nums[i]) * rightMaxs[i + 1],
                            (long) (leftMaxs[i - 1] - nums[i]) * rightMaxs[i + 1])))
            .max()
            .getAsLong());
  }
}
