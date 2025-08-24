import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean partitionArray(int[] nums, int k) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : nums) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    return nums.length % k == 0
        && valueToCount.values().stream().mapToInt(Integer::intValue).max().getAsInt()
            <= nums.length / k;
  }
}