import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int LIMIT = 1 << 10;

  public int minChanges(int[] nums, int k) {
    @SuppressWarnings("unchecked")
    Map<Integer, Integer>[] valueMaps = new Map[k];
    for (int i = 0; i < valueMaps.length; ++i) {
      valueMaps[i] = new HashMap<>();
    }
    for (int i = 0; i < nums.length; ++i) {
      valueMaps[i % k].put(nums[i], valueMaps[i % k].getOrDefault(nums[i], 0) + 1);
    }

    int[] dp = new int[LIMIT];
    for (int i = 0; i < k; ++i) {
      int[] nextDp = new int[dp.length];
      Arrays.fill(nextDp, Arrays.stream(dp).max().getAsInt());

      for (int j = 0; j < ((i == 0) ? 1 : dp.length); ++j) {
        for (int value : valueMaps[i].keySet()) {
          nextDp[j ^ value] = Math.max(nextDp[j ^ value], dp[j] + valueMaps[i].get(value));
        }
      }

      dp = nextDp;
    }

    return nums.length - dp[0];
  }
}
