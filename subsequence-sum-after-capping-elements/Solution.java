import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public boolean[] subsequenceSumAfterCapping(int[] nums, int k) {
    Arrays.sort(nums);

    boolean[] dp = new boolean[k + 1];
    dp[0] = true;

    boolean[] result = new boolean[nums.length];
    int index = -1;
    for (int i = 0; i < result.length; ++i) {
      while (index != nums.length - 1 && nums[index + 1] <= i + 1) {
        update(dp, nums[index + 1]);
        ++index;
      }

      int i_ = i;
      result[i] =
          IntStream.rangeClosed(0, nums.length - 1 - index)
              .anyMatch(j -> k - j * (i_ + 1) >= 0 && dp[k - j * (i_ + 1)]);
    }

    return result;
  }

  void update(boolean[] dp, int value) {
    for (int i = dp.length - 1; i >= value; --i) {
      dp[i] |= dp[i - value];
    }
  }
}