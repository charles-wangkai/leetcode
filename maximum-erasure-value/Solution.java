import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maximumUniqueSubarray(int[] nums) {
    int result = 0;
    int beginIndex = 0;
    Map<Integer, Integer> valueToCount = new HashMap<>();
    int sum = 0;
    for (int endIndex = 0; endIndex < nums.length; ++endIndex) {
      sum += nums[endIndex];
      valueToCount.put(nums[endIndex], valueToCount.getOrDefault(nums[endIndex], 0) + 1);

      while (valueToCount.get(nums[endIndex]) != 1) {
        sum -= nums[beginIndex];
        valueToCount.put(nums[beginIndex], valueToCount.get(nums[beginIndex]) - 1);
        ++beginIndex;
      }

      result = Math.max(result, sum);
    }

    return result;
  }
}
