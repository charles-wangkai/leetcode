import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> valueToIndex = new HashMap<>();
    for (int i = 0; ; ++i) {
      Integer otherIndex = valueToIndex.get(target - nums[i]);
      if (otherIndex != null) {
        return new int[] {otherIndex, i};
      }

      valueToIndex.put(nums[i], i);
    }
  }
}
