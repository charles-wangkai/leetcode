import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public long maxSum(List<Integer> nums, int m, int k) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    long sum = 0;
    for (int i = 0; i < k - 1; ++i) {
      updateMap(valueToCount, nums.get(i), 1);
      sum += nums.get(i);
    }

    long result = 0;
    for (int i = k - 1; i < nums.size(); ++i) {
      updateMap(valueToCount, nums.get(i), 1);
      sum += nums.get(i);

      if (valueToCount.size() >= m) {
        result = Math.max(result, sum);
      }

      updateMap(valueToCount, nums.get(i - k + 1), -1);
      sum -= nums.get(i - k + 1);
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}
