import java.util.HashMap;
import java.util.Map;

class Solution {
  public int mostFrequent(int[] nums, int key) {
    Map<Integer, Integer> targetToCount = new HashMap<>();
    for (int i = 1; i < nums.length; ++i) {
      if (nums[i - 1] == key) {
        targetToCount.put(nums[i], targetToCount.getOrDefault(nums[i], 0) + 1);
      }
    }

    int maxCount = targetToCount.values().stream().mapToInt(x -> x).max().getAsInt();

    return targetToCount.keySet().stream()
        .filter(target -> targetToCount.get(target) == maxCount)
        .findAny()
        .get();
  }
}