import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int firstUniqueFreq(int[] nums) {
    Map<Integer, Integer> valueToFreq = new HashMap<>();
    for (int num : nums) {
      valueToFreq.put(num, valueToFreq.getOrDefault(num, 0) + 1);
    }

    Map<Integer, Integer> freqToCount = new HashMap<>();
    for (int freq : valueToFreq.values()) {
      freqToCount.put(freq, freqToCount.getOrDefault(freq, 0) + 1);
    }

    return Arrays.stream(nums)
        .filter(num -> freqToCount.get(valueToFreq.get(num)) == 1)
        .findFirst()
        .orElse(-1);
  }
}