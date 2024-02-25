import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean isPossibleToSplit(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    return valueToCount.values().stream().allMatch(count -> count <= 2);
  }
}