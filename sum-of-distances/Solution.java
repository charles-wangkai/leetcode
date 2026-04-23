import java.util.HashMap;
import java.util.Map;

class Solution {
  public long[] distance(int[] nums) {
    Map<Integer, Integer> valueToRightCount = new HashMap<>();
    Map<Integer, Long> valueToRightIndexSum = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      valueToRightCount.put(nums[i], valueToRightCount.getOrDefault(nums[i], 0) + 1);
      valueToRightIndexSum.put(nums[i], valueToRightIndexSum.getOrDefault(nums[i], 0L) + i);
    }

    long[] result = new long[nums.length];
    Map<Integer, Integer> valueToLeftCount = new HashMap<>();
    Map<Integer, Long> valueToLeftIndexSum = new HashMap<>();
    for (int i = 0; i < result.length; ++i) {
      valueToRightCount.put(nums[i], valueToRightCount.get(nums[i]) - 1);
      valueToRightIndexSum.put(nums[i], valueToRightIndexSum.get(nums[i]) - i);

      valueToLeftCount.put(nums[i], valueToLeftCount.getOrDefault(nums[i], 0) + 1);
      valueToLeftIndexSum.put(nums[i], valueToLeftIndexSum.getOrDefault(nums[i], 0L) + i);

      result[i] =
          ((long) i * valueToLeftCount.get(nums[i]) - valueToLeftIndexSum.get(nums[i]))
              + (valueToRightIndexSum.get(nums[i]) - (long) i * valueToRightCount.get(nums[i]));
    }

    return result;
  }
}
