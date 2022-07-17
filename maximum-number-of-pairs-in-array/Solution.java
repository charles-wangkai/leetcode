import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] numberOfPairs(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : nums) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    int pairNum = valueToCount.values().stream().mapToInt(count -> count / 2).sum();

    return new int[] {pairNum, nums.length - 2 * pairNum};
  }
}