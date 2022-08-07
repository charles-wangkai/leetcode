import java.util.HashMap;
import java.util.Map;

class Solution {
  public long countBadPairs(int[] nums) {
    Map<Integer, Integer> deltaToCount = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      int delta = nums[i] - i;
      deltaToCount.put(delta, deltaToCount.getOrDefault(delta, 0) + 1);
    }

    return deltaToCount.values().stream()
            .mapToLong(count -> (long) count * (nums.length - count))
            .sum()
        / 2;
  }
}