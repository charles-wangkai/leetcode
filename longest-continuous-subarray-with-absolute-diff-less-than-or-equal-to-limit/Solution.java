import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int longestSubarray(int[] nums, int limit) {
    int result = 0;
    SortedMap<Integer, Integer> valueToCount = new TreeMap<>();
    int beginIndex = 0;
    for (int endIndex = 0; endIndex < nums.length; ++endIndex) {
      valueToCount.put(nums[endIndex], valueToCount.getOrDefault(nums[endIndex], 0) + 1);

      while (valueToCount.lastKey() - valueToCount.firstKey() > limit) {
        valueToCount.put(nums[beginIndex], valueToCount.get(nums[beginIndex]) - 1);
        valueToCount.remove(nums[beginIndex], 0);
        ++beginIndex;
      }

      result = Math.max(result, endIndex - beginIndex + 1);
    }

    return result;
  }
}