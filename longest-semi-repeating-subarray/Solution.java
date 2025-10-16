import java.util.HashMap;
import java.util.Map;

class Solution {
  public int longestSubarray(int[] nums, int k) {
    int result = 0;
    int beginIndex = 0;
    Map<Integer, Integer> valueToCount = new HashMap<>();
    int repeatCount = 0;
    for (int endIndex = 0; endIndex < nums.length; ++endIndex) {
      updateMap(valueToCount, nums[endIndex], 1);
      if (valueToCount.get(nums[endIndex]) == 2) {
        ++repeatCount;
      }

      while (repeatCount == k + 1) {
        updateMap(valueToCount, nums[beginIndex], -1);
        if (valueToCount.getOrDefault(nums[beginIndex], 0) == 1) {
          --repeatCount;
        }

        ++beginIndex;
      }

      result = Math.max(result, endIndex - beginIndex + 1);
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}