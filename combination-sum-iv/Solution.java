import java.util.HashMap;
import java.util.Map;

class Solution {
  public int combinationSum4(int[] nums, int target) {
    int result = 0;

    Map<Integer, Integer> sumToCount = Map.of(0, 1);
    while (!sumToCount.isEmpty()) {
      Map<Integer, Integer> nextSumToCount = new HashMap<>();
      for (int sum : sumToCount.keySet()) {
        for (int num : nums) {
          int nextSum = sum + num;
          if (nextSum < target) {
            nextSumToCount.put(
                nextSum, nextSumToCount.getOrDefault(nextSum, 0) + sumToCount.get(sum));
          } else if (nextSum == target) {
            result += sumToCount.get(sum);
          }
        }
      }

      sumToCount = nextSumToCount;
    }

    return result;
  }
}
