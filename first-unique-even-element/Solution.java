import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int firstUniqueEven(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : nums) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    return IntStream.range(0, nums.length)
        .filter(i -> nums[i] % 2 == 0 && valueToCount.get(nums[i]) == 1)
        .map(i -> nums[i])
        .findFirst()
        .orElse(-1);
  }
}