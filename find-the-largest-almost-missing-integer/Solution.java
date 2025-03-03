import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int largestInteger(int[] nums, int k) {
    if (k == nums.length) {
      return Arrays.stream(nums).max().getAsInt();
    }

    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : nums) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }
    if (k == 1) {
      return valueToCount.keySet().stream()
          .filter(value -> valueToCount.get(value) == 1)
          .mapToInt(Integer::intValue)
          .max()
          .orElse(-1);
    }

    return IntStream.of(nums[0], nums[nums.length - 1])
        .filter(value -> valueToCount.get(value) == 1)
        .max()
        .orElse(-1);
  }
}