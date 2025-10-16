import java.util.HashMap;
import java.util.Map;

class Solution {
  public int findSmallestInteger(int[] nums, int value) {
    Map<Integer, Integer> remainderToCount = new HashMap<>();
    for (int num : nums) {
      update(remainderToCount, Math.floorMod(num, value), 1);
    }

    for (int i = 0; ; ++i) {
      int remainder = i % value;
      if (!remainderToCount.containsKey(remainder)) {
        return i;
      }

      update(remainderToCount, remainder, -1);
    }
  }

  void update(Map<Integer, Integer> remainderToCount, int remainder, int delta) {
    remainderToCount.put(remainder, remainderToCount.getOrDefault(remainder, 0) + delta);
    remainderToCount.remove(remainder, 0);
  }
}
