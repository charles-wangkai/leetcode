import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int[] getSubarrayBeauty(int[] nums, int k, int x) {
    SortedMap<Integer, Integer> negativeToCount = new TreeMap<>();
    for (int i = 0; i < k - 1; ++i) {
      updateMap(negativeToCount, nums[i], 1);
    }

    int[] result = new int[nums.length - k + 1];
    for (int i = 0; i < result.length; ++i) {
      updateMap(negativeToCount, nums[i + k - 1], 1);
      result[i] = computeBeauty(negativeToCount, x);

      updateMap(negativeToCount, nums[i], -1);
    }

    return result;
  }

  int computeBeauty(SortedMap<Integer, Integer> negativeToCount, int x) {
    int countSum = 0;
    for (int negative : negativeToCount.keySet()) {
      countSum += negativeToCount.get(negative);
      if (countSum >= x) {
        return negative;
      }
    }

    return 0;
  }

  void updateMap(SortedMap<Integer, Integer> negativeToCount, int value, int delta) {
    if (value <= 0) {
      negativeToCount.put(value, negativeToCount.getOrDefault(value, 0) + delta);
    }
  }
}
