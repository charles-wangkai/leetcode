import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] delayedCount(int[] nums, int k) {
    int[] result = new int[nums.length];
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int i = result.length - 1; i >= 0; --i) {
      if (i + k + 1 < nums.length) {
        valueToCount.put(nums[i + k + 1], valueToCount.getOrDefault(nums[i + k + 1], 0) + 1);
      }

      result[i] = valueToCount.getOrDefault(nums[i], 0);
    }

    return result;
  }
}