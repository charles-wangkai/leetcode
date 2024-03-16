import java.util.HashMap;
import java.util.Map;

class Solution {
  public int findMaxLength(int[] nums) {
    Map<Integer, Integer> deltaToFirstIndex = new HashMap<>();
    deltaToFirstIndex.put(0, -1);

    int result = 0;
    int delta = 0;
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] == 0) {
        ++delta;
      } else {
        --delta;
      }

      if (deltaToFirstIndex.containsKey(delta)) {
        result = Math.max(result, i - deltaToFirstIndex.get(delta));
      } else {
        deltaToFirstIndex.put(delta, i);
      }
    }

    return result;
  }
}
