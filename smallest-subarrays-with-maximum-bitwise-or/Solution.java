import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] smallestSubarrays(int[] nums) {
    int[] result = new int[nums.length];
    Map<Integer, Integer> posToIndex = new HashMap<>();
    for (int i = result.length - 1; i >= 0; --i) {
      for (int pos = 0; (1 << pos) <= nums[i]; ++pos) {
        if (((nums[i] >> pos) & 1) == 1) {
          posToIndex.put(pos, i);
        }
      }

      result[i] = posToIndex.values().stream().mapToInt(Integer::intValue).max().orElse(i) - i + 1;
    }

    return result;
  }
}