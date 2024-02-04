import java.util.HashMap;
import java.util.Map;

class Solution {
  public long maximumSubarraySum(int[] nums, int k) {
    long result = Long.MIN_VALUE;
    long prefixSum = 0;
    Map<Integer, Long> valueToMinPrefixSum = new HashMap<>();
    for (int num : nums) {
      if (valueToMinPrefixSum.containsKey(num - k)) {
        result = Math.max(result, prefixSum + num - valueToMinPrefixSum.get(num - k));
      }
      if (valueToMinPrefixSum.containsKey(num + k)) {
        result = Math.max(result, prefixSum + num - valueToMinPrefixSum.get(num + k));
      }

      valueToMinPrefixSum.put(
          num, Math.min(valueToMinPrefixSum.getOrDefault(num, Long.MAX_VALUE), prefixSum));
      prefixSum += num;
    }

    return (result == Long.MIN_VALUE) ? 0 : result;
  }
}