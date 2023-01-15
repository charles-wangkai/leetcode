import java.util.HashMap;
import java.util.Map;

class Solution {
  public long countGood(int[] nums, int k) {
    long result = 0;
    int endIndex = -1;
    Map<Integer, Integer> valueToCount = new HashMap<>();
    long pairNum = 0;
    for (int i = 0; i < nums.length; ++i) {
      while (pairNum < k && endIndex + 1 != nums.length) {
        ++endIndex;
        update(valueToCount, nums[endIndex], 1);

        int count = valueToCount.get(nums[endIndex]);
        pairNum += countPairs(count) - countPairs(count - 1);
      }
      if (pairNum < k) {
        break;
      }

      result += nums.length - endIndex;

      update(valueToCount, nums[i], -1);

      int count = valueToCount.get(nums[i]);
      pairNum += countPairs(count) - countPairs(count + 1);
    }

    return result;
  }

  void update(Map<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
  }

  long countPairs(int count) {
    return count * (count - 1L) / 2;
  }
}
