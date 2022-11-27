import java.util.HashMap;
import java.util.Map;

class Solution {
  public int numberOfArithmeticSlices(int[] nums) {
    @SuppressWarnings("unchecked")
    Map<Integer, Integer>[] deltaToCountMaps = new Map[nums.length];
    for (int i = 0; i < deltaToCountMaps.length; ++i) {
      deltaToCountMaps[i] = new HashMap<>();
    }

    int result = 0;
    for (int i = 0; i < nums.length; ++i) {
      for (int j = 0; j < i; ++j) {
        long d = (long) nums[i] - nums[j];
        if (d >= Integer.MIN_VALUE && d <= Integer.MAX_VALUE) {
          int delta = (int) d;

          int prevCount = deltaToCountMaps[j].getOrDefault(delta, 0);
          deltaToCountMaps[i].put(
              delta, deltaToCountMaps[i].getOrDefault(delta, 0) + prevCount + 1);
          result += prevCount;
        }
      }
    }

    return result;
  }
}
