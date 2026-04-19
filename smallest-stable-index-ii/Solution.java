import java.util.stream.IntStream;

class Solution {
  public int firstStableIndex(int[] nums, int k) {
    int[] leftMaxs = new int[nums.length];
    for (int i = 0; i < leftMaxs.length; ++i) {
      leftMaxs[i] = Math.max((i == 0) ? Integer.MIN_VALUE : leftMaxs[i - 1], nums[i]);
    }

    int[] rightMins = new int[nums.length];
    for (int i = rightMins.length - 1; i >= 0; --i) {
      rightMins[i] =
          Math.min((i == rightMins.length - 1) ? Integer.MAX_VALUE : rightMins[i + 1], nums[i]);
    }

    return IntStream.range(0, nums.length)
        .filter(i -> leftMaxs[i] - rightMins[i] <= k)
        .findFirst()
        .orElse(-1);
  }
}