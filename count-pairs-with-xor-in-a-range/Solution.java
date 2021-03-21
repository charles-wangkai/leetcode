import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int DIGIT_NUM = 15;

  public int countPairs(int[] nums, int low, int high) {
    @SuppressWarnings("unchecked")
    Map<Integer, Integer>[] countMaps = new Map[DIGIT_NUM];
    for (int i = 0; i < countMaps.length; ++i) {
      countMaps[i] = new HashMap<>();
    }
    for (int num : nums) {
      int mask = 0;
      for (int i = countMaps.length - 1; i >= 0; --i) {
        mask += 1 << i;
        int key = num & mask;

        countMaps[i].put(key, countMaps[i].getOrDefault(key, 0) + 1);
      }
    }

    return computePairNum(nums, countMaps, high) - computePairNum(nums, countMaps, low - 1);
  }

  int computePairNum(int[] nums, Map<Integer, Integer>[] countMaps, int limit) {
    int result = 0;
    for (int num : nums) {
      int count = countMaps[0].getOrDefault(num ^ limit, 0) - 1;
      int prefix = 0;
      for (int i = countMaps.length - 1; i >= 0; --i) {
        if ((limit & (1 << i)) != 0) {
          count += countMaps[i].getOrDefault(prefix + (num & (1 << i)), 0);
        }

        prefix += (num & (1 << i)) ^ (limit & (1 << i));
      }

      result += count;
    }
    result /= 2;

    return result;
  }
}
