import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean canDivideIntoSubsequences(int[] nums, int k) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    return (long) valueToCount.values().stream().mapToInt(Integer::intValue).max().getAsInt() * k
        <= nums.length;
  }
}
