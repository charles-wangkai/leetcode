import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minOperations(int[] nums) {
    int limit = Arrays.stream(nums).max().getAsInt() * 2;

    Map<Integer, Integer> valueToOperationNum = Map.of(nums[0], 0);
    for (int i = 1; i < nums.length; ++i) {
      Map<Integer, Integer> nextValueToOperationNum = new HashMap<>();
      for (int value : valueToOperationNum.keySet()) {
        for (int next = nums[i]; next <= limit; ++next) {
          if (next % value == 0) {
            nextValueToOperationNum.put(
                next,
                Math.min(
                    nextValueToOperationNum.getOrDefault(next, Integer.MAX_VALUE),
                    valueToOperationNum.get(value) + (next - nums[i])));
          }
        }
      }

      valueToOperationNum = nextValueToOperationNum;
    }

    return valueToOperationNum.values().stream().mapToInt(Integer::intValue).min().getAsInt();
  }
}