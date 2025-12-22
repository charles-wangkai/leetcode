import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minOperations(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : nums) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    int duplicateCount = (int) valueToCount.values().stream().filter(count -> count > 1).count();

    int result = 0;
    int index = 0;
    while (duplicateCount != 0) {
      for (int i = 0; i < 3 && index != nums.length; ++i) {
        valueToCount.put(nums[index], valueToCount.get(nums[index]) - 1);
        if (valueToCount.get(nums[index]) == 1) {
          --duplicateCount;
        }

        ++index;
      }

      ++result;
    }

    return result;
  }
}