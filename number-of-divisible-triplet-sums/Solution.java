import java.util.HashMap;
import java.util.Map;

class Solution {
  public int divisibleTripletCount(int[] nums, int d) {
    int result = 0;
    Map<Integer, Integer> remainderToCount = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i + 1; j < nums.length; ++j) {
        result += remainderToCount.getOrDefault(Math.floorMod(-nums[i] - nums[j], d), 0);
      }

      remainderToCount.put(nums[i] % d, remainderToCount.getOrDefault(nums[i] % d, 0) + 1);
    }

    return result;
  }
}