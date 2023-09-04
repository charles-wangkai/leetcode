import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
    long result = 0;
    Map<Integer, Integer> validNumToCount = new HashMap<>();
    validNumToCount.put(0, 1);
    int validNum = 0;
    for (int num : nums) {
      validNum = (validNum + ((num % modulo == k) ? 1 : 0)) % modulo;

      result += validNumToCount.getOrDefault(Math.floorMod(validNum - k, modulo), 0);

      validNumToCount.put(validNum, validNumToCount.getOrDefault(validNum, 0) + 1);
    }

    return result;
  }
}
