import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean isMiddleElementUnique(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    return valueToCount.get(nums[nums.length / 2]) == 1;
  }
}