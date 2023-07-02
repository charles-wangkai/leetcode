import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public long continuousSubarrays(int[] nums) {
    long result = 0;
    int beginIndex = 0;
    SortedMap<Integer, Integer> valueToCount = new TreeMap<>();
    for (int i = 0; i < nums.length; ++i) {
      updateMap(valueToCount, nums[i], 1);
      while (valueToCount.lastKey() - valueToCount.firstKey() > 2) {
        updateMap(valueToCount, nums[beginIndex], -1);
        ++beginIndex;
      }

      result += i - beginIndex + 1;
    }

    return result;
  }

  void updateMap(SortedMap<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}
