import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public long countSubarrays(int[] nums, long k) {
    long result = 0;
    SortedMap<Integer, Integer> valueToCount = new TreeMap<>();
    int endIndex = -1;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      while (endIndex != nums.length - 1
          && (valueToCount.isEmpty()
              || Math.max(
                          Math.max(
                              Math.abs(nums[endIndex + 1] - valueToCount.firstKey()),
                              Math.abs(nums[endIndex + 1] - valueToCount.lastKey())),
                          valueToCount.lastKey() - valueToCount.firstKey())
                      * (endIndex - beginIndex + 2L)
                  <= k)) {
        ++endIndex;
        updateMap(valueToCount, nums[endIndex], 1);
      }

      result += endIndex - beginIndex + 1;

      updateMap(valueToCount, nums[beginIndex], -1);
    }

    return result;
  }

  void updateMap(SortedMap<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}