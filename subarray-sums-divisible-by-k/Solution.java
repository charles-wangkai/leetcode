import java.util.HashMap;
import java.util.Map;

class Solution {
  public int subarraysDivByK(int[] nums, int k) {
    Map<Integer, Integer> remainderToCount = new HashMap<>();
    remainderToCount.put(0, 1);

    int result = 0;
    int remainder = 0;
    for (int num : nums) {
      remainder = Math.floorMod(remainder + num, k);
      result += remainderToCount.getOrDefault(remainder, 0);
      remainderToCount.put(remainder, remainderToCount.getOrDefault(remainder, 0) + 1);
    }

    return result;
  }
}
