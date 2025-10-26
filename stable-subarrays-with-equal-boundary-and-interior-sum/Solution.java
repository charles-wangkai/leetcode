import java.util.HashMap;
import java.util.Map;

class Solution {
  public long countStableSubarrays(int[] capacity) {
    long result = 0;
    Map<Integer, Map<Long, Integer>> valueToPrefixSumMap = new HashMap<>();
    long prefixSum = 0;
    for (int i = 0; i < capacity.length; ++i) {
      prefixSum += capacity[i];
      result +=
          valueToPrefixSumMap
                  .getOrDefault(capacity[i], Map.of())
                  .getOrDefault(prefixSum - 2 * capacity[i], 0)
              - ((capacity[i] == 0 && i != 0 && capacity[i - 1] == 0) ? 1 : 0);

      valueToPrefixSumMap.putIfAbsent(capacity[i], new HashMap<>());

      Map<Long, Integer> prefixSumMap = valueToPrefixSumMap.get(capacity[i]);
      prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
    }

    return result;
  }
}