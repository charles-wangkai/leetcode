import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minLength(int[] nums, int k) {
    int result = Integer.MAX_VALUE;
    int endIndex = -1;
    Map<Integer, Integer> valueToCount = new HashMap<>();
    long sum = 0;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      while (endIndex != nums.length - 1 && sum < k) {
        ++endIndex;

        valueToCount.put(nums[endIndex], valueToCount.getOrDefault(nums[endIndex], 0) + 1);
        if (valueToCount.get(nums[endIndex]) == 1) {
          sum += nums[endIndex];
        }
      }

      if (sum >= k) {
        result = Math.min(result, endIndex - beginIndex + 1);
      }

      valueToCount.put(nums[beginIndex], valueToCount.get(nums[beginIndex]) - 1);
      valueToCount.remove(nums[beginIndex], 0);
      if (!valueToCount.containsKey(nums[beginIndex])) {
        sum -= nums[beginIndex];
      }
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }
}