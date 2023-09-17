import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int minLengthAfterRemovals(List<Integer> nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    int maxCount = valueToCount.values().stream().mapToInt(Integer::intValue).max().getAsInt();

    return nums.size()
        - ((maxCount <= nums.size() / 2) ? (nums.size() / 2) : (nums.size() - maxCount)) * 2;
  }
}
