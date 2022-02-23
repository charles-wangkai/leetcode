import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public long maximumAlternatingSubarraySum(int[] nums) {
    SortedMap<Long, Integer> alternateSumToCount = new TreeMap<>();
    long alternateSum = 0;
    for (int i = 0; i < nums.length; ++i) {
      alternateSum += ((i % 2 == 0) ? 1 : -1) * nums[i];
      update(alternateSumToCount, alternateSum, 1);
    }

    long result = alternateSumToCount.lastKey();
    alternateSum = 0;
    for (int i = 0; i < nums.length - 1; ++i) {
      alternateSum += ((i % 2 == 0) ? 1 : -1) * nums[i];
      update(alternateSumToCount, alternateSum, -1);

      result =
          Math.max(
              result,
              (i % 2 == 0)
                  ? -(alternateSumToCount.firstKey() - alternateSum)
                  : (alternateSumToCount.lastKey() - alternateSum));
    }

    return result;
  }

  void update(SortedMap<Long, Integer> alternateSumToCount, long key, int delta) {
    alternateSumToCount.put(key, alternateSumToCount.getOrDefault(key, 0) + delta);
    alternateSumToCount.remove(key, 0);
  }
}