import java.util.HashMap;
import java.util.Map;

class Solution {
  public int subarraySum(int[] nums, int k) {
    Map<Integer, Integer> sumToCount = new HashMap<>();
    sumToCount.put(0, 1);

    int result = 0;
    int sum = 0;
    for (int num : nums) {
      sum += num;
      result += sumToCount.getOrDefault(sum - k, 0);
      sumToCount.put(sum, sumToCount.getOrDefault(sum, 0) + 1);
    }

    return result;
  }
}
