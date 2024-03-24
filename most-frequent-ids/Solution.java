import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public long[] mostFrequentIDs(int[] nums, int[] freq) {
    long[] result = new long[nums.length];
    Map<Integer, Long> idToFreq = new HashMap<>();
    SortedMap<Long, Integer> freqToCount = new TreeMap<>();
    for (int i = 0; i < result.length; ++i) {
      if (idToFreq.getOrDefault(nums[i], 0L) != 0) {
        updateMap(freqToCount, idToFreq.get(nums[i]), -1);
      }

      idToFreq.put(nums[i], idToFreq.getOrDefault(nums[i], 0L) + freq[i]);
      if (idToFreq.get(nums[i]) != 0) {
        updateMap(freqToCount, idToFreq.get(nums[i]), 1);
      }

      result[i] = freqToCount.isEmpty() ? 0 : freqToCount.lastKey();
    }

    return result;
  }

  void updateMap(SortedMap<Long, Integer> freqToCount, long freq, int delta) {
    freqToCount.put(freq, freqToCount.getOrDefault(freq, 0) + delta);
    freqToCount.remove(freq, 0);
  }
}