import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maxSubarrayLength(int[] nums, int k) {
    int result = 0;
    Map<Integer, Integer> valueToCount = new HashMap<>();
    int beginIndex = 0;
    for (int i = 0; i < nums.length; ++i) {
      valueToCount.put(nums[i], valueToCount.getOrDefault(nums[i], 0) + 1);
      while (valueToCount.get(nums[i]) == k + 1) {
        valueToCount.put(nums[beginIndex], valueToCount.get(nums[beginIndex]) - 1);
        ++beginIndex;
      }

      result = Math.max(result, i - beginIndex + 1);
    }

    return result;
  }
}