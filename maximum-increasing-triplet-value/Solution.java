import java.util.NavigableMap;
import java.util.TreeMap;

class Solution {
  public int maximumTripletValue(int[] nums) {
    NavigableMap<Integer, Integer> valueToCount = new TreeMap<>();
    for (int i = 0; i < nums.length - 1; ++i) {
      updateMap(valueToCount, nums[i], 1);
    }

    int result = 0;
    int rightMax = 0;
    for (int i = nums.length - 2; i >= 1; --i) {
      rightMax = Math.max(rightMax, nums[i + 1]);
      updateMap(valueToCount, nums[i], -1);

      Integer leftMax = valueToCount.lowerKey(nums[i]);
      if (leftMax != null && rightMax > nums[i]) {
        result = Math.max(result, leftMax - nums[i] + rightMax);
      }
    }

    return result;
  }

  void updateMap(NavigableMap<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}