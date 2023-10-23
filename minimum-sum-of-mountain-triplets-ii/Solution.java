import java.util.stream.IntStream;

class Solution {
  public int minimumSum(int[] nums) {
    int[] leftMins = new int[nums.length];
    for (int i = 0; i < leftMins.length; ++i) {
      leftMins[i] = Math.min(nums[i], (i == 0) ? Integer.MAX_VALUE : leftMins[i - 1]);
    }

    int[] rightMins = new int[nums.length];
    for (int i = rightMins.length - 1; i >= 0; --i) {
      rightMins[i] =
          Math.min(nums[i], (i == rightMins.length - 1) ? Integer.MAX_VALUE : rightMins[i + 1]);
    }

    return IntStream.rangeClosed(1, nums.length - 2)
        .filter(i -> nums[i] > leftMins[i - 1] && nums[i] > rightMins[i + 1])
        .map(i -> nums[i] + leftMins[i - 1] + rightMins[i + 1])
        .min()
        .orElse(-1);
  }
}
