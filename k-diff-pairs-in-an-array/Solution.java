import java.util.HashMap;
import java.util.Map;

class Solution {
  public int findPairs(int[] nums, int k) {
    Map<Integer, Integer> numToCount = new HashMap<>();
    for (int num : nums) {
      numToCount.put(num, numToCount.getOrDefault(num, 0) + 1);
    }

    return (k == 0)
        ? (int) numToCount.values().stream().filter(count -> count != 1).count()
        : (int) numToCount.keySet().stream().filter(num -> numToCount.containsKey(num + k)).count();
  }
}
