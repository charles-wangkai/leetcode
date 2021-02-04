import java.util.HashMap;
import java.util.Map;

class Solution {
  public int findLHS(int[] nums) {
    Map<Integer, Integer> numToCount = new HashMap<>();
    for (int num : nums) {
      numToCount.put(num, numToCount.getOrDefault(num, 0) + 1);
    }

    return numToCount.keySet().stream()
        .filter(num -> numToCount.containsKey(num + 1))
        .mapToInt(num -> numToCount.get(num) + numToCount.get(num + 1))
        .max()
        .orElse(0);
  }
}
