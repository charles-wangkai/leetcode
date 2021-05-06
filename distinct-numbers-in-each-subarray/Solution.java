import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] distinctNumbers(int[] nums, int k) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int i = 0; i < k - 1; ++i) {
      updateMap(valueToCount, nums[i], 1);
    }

    int[] result = new int[nums.length - k + 1];
    for (int i = 0; i < result.length; ++i) {
      updateMap(valueToCount, nums[i + k - 1], 1);
      result[i] = valueToCount.size();
      updateMap(valueToCount, nums[i], -1);
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}
