import java.util.HashMap;
import java.util.Map;

class Solution {
  public int countStaircase(int[] nums) {
    int result = 0;
    for (int i = 0; i < nums.length; ++i) {
      Map<Integer, Integer> valueToIndex = new HashMap<>();
      Map<Integer, Integer> valueToFreq = new HashMap<>();
      int correctFreqCount = 0;
      for (int j = i; j < nums.length; ++j) {
        if (!valueToIndex.containsKey(nums[j])) {
          valueToIndex.put(nums[j], valueToIndex.size());
        }

        if (valueToFreq.getOrDefault(nums[j], 0) == valueToIndex.get(nums[j]) + 1) {
          --correctFreqCount;
        }

        valueToFreq.put(nums[j], valueToFreq.getOrDefault(nums[j], 0) + 1);

        if (valueToFreq.get(nums[j]) == valueToIndex.get(nums[j]) + 1) {
          ++correctFreqCount;
        }

        if (correctFreqCount == valueToIndex.size()) {
          ++result;
        }
      }
    }

    return result;
  }
}