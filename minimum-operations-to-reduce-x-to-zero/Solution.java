import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minOperations(int[] nums, int x) {
    Map<Integer, Integer> rightSumToLength = new HashMap<>();
    rightSumToLength.put(0, 0);
    int rightSum = 0;
    for (int i = nums.length - 1; i >= 0; --i) {
      rightSum += nums[i];
      rightSumToLength.put(rightSum, nums.length - i);
    }

    int result = rightSumToLength.getOrDefault(x, nums.length + 1);
    int leftSum = 0;
    for (int i = 0; i < nums.length; ++i) {
      leftSum += nums[i];
      if (rightSumToLength.containsKey(x - leftSum)) {
        result = Math.min(result, i + 1 + rightSumToLength.get(x - leftSum));
      }
    }

    return (result == nums.length + 1) ? -1 : result;
  }
}
