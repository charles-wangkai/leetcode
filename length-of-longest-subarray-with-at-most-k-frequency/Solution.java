import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maxSubarrayLength(int[] nums, int k) {
    int result = 0;
    Map<Integer, Integer> valueToCount = new HashMap<>();
    int beginIndex = 0;
    for (int endIndex = 0; endIndex < nums.length; ++endIndex) {
      valueToCount.put(nums[endIndex], valueToCount.getOrDefault(nums[endIndex], 0) + 1);
      while (valueToCount.get(nums[endIndex]) == k + 1) {
        valueToCount.put(nums[beginIndex], valueToCount.get(nums[beginIndex]) - 1);
        ++beginIndex;
      }

      result = Math.max(result, endIndex - beginIndex + 1);
    }

    return result;
  }
}