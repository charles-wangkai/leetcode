import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int subarraysWithMoreZerosThanOnes(int[] nums) {
    Map<Integer, Integer> lowerSumToCount = new HashMap<>();
    Map<Integer, Integer> upperSumToCount = new HashMap<>();
    int upperNum = 0;
    int sum = 0;
    for (int num : nums) {
      sum += (num == 1) ? 1 : -1;
      if (sum >= 1) {
        upperSumToCount.put(sum, upperSumToCount.getOrDefault(sum, 0) + 1);
        ++upperNum;
      } else {
        lowerSumToCount.put(sum, lowerSumToCount.getOrDefault(sum, 0) + 1);
      }
    }

    long result = upperNum;
    int prefixSum = 0;
    for (int num : nums) {
      if (num == 0) {
        --prefixSum;

        lowerSumToCount.put(prefixSum, lowerSumToCount.get(prefixSum) - 1);

        if (lowerSumToCount.containsKey(prefixSum + 1)) {
          int count = lowerSumToCount.get(prefixSum + 1);
          lowerSumToCount.remove(prefixSum + 1);
          upperSumToCount.put(prefixSum + 1, count);

          upperNum += count;
        }
      } else {
        ++prefixSum;

        upperSumToCount.put(prefixSum, upperSumToCount.get(prefixSum) - 1);
        --upperNum;

        if (upperSumToCount.containsKey(prefixSum)) {
          int count = upperSumToCount.get(prefixSum);
          upperSumToCount.remove(prefixSum);
          lowerSumToCount.put(prefixSum, count);

          upperNum -= count;
        }
      }

      result += upperNum;
    }

    return (int) (result % MODULUS);
  }
}