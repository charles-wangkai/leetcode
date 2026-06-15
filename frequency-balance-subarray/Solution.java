import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int getLength(int[] nums) {
    int result = -1;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      Map<Integer, Integer> valueToFreq = new HashMap<>();
      SortedMap<Integer, Integer> freqToCount = new TreeMap<>();
      for (int endIndex = beginIndex; endIndex < nums.length; ++endIndex) {
        if (valueToFreq.containsKey(nums[endIndex])) {
          updateMap(freqToCount, valueToFreq.get(nums[endIndex]), -1);
        }
        updateMap(valueToFreq, nums[endIndex], 1);
        updateMap(freqToCount, valueToFreq.get(nums[endIndex]), 1);

        if (valueToFreq.size() == 1
            || (freqToCount.size() == 2 && freqToCount.firstKey() * 2 == freqToCount.lastKey())) {
          result = Math.max(result, endIndex - beginIndex + 1);
        }
      }
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> map, int key, int delta) {
    map.put(key, map.getOrDefault(key, 0) + delta);
    map.remove(key, 0);
  }
}