import java.util.HashMap;
import java.util.Map;

class Solution {
  public long maximumSubarraySum(int[] nums, int k) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    long sum = 0;
    for (int i = 0; i < k - 1; ++i) {
      sum += nums[i];
      updateMap(valueToCount, nums[i], 1);
    }

    long result = 0;
    for (int i = k - 1; i < nums.length; ++i) {
      sum += nums[i];
      updateMap(valueToCount, nums[i], 1);
      if (valueToCount.size() == k) {
        result = Math.max(result, sum);
      }

      sum -= nums[i - k + 1];
      updateMap(valueToCount, nums[i - k + 1], -1);
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}
