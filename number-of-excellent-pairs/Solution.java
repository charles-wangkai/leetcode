import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public long countExcellentPairs(int[] nums, int k) {
    Map<Integer, Integer> bitNumToCount = new HashMap<>();
    for (int num : Arrays.stream(nums).distinct().toArray()) {
      int bitNum = Integer.bitCount(num);
      bitNumToCount.put(bitNum, bitNumToCount.getOrDefault(bitNum, 0) + 1);
    }

    long result = 0;
    for (int bitNum1 : bitNumToCount.keySet()) {
      for (int bitNum2 : bitNumToCount.keySet()) {
        if (bitNum1 + bitNum2 >= k) {
          result += (long) bitNumToCount.get(bitNum1) * bitNumToCount.get(bitNum2);
        }
      }
    }

    return result;
  }
}