import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minSizeSubarray(int[] nums, int target) {
    long total = Arrays.stream(nums).asLongStream().sum();

    int result = (target % total == 0) ? (int) (target / total * nums.length) : Integer.MAX_VALUE;

    Map<Long, Integer> leftSumToLength = new HashMap<>();
    leftSumToLength.put(0L, 0);
    long leftSum = 0;
    for (int i = 0; i < nums.length; ++i) {
      leftSum += nums[i];
      leftSumToLength.put(leftSum, i + 1);

      if (leftSumToLength.containsKey(leftSum - target)) {
        result = Math.min(result, i + 1 - leftSumToLength.get(leftSum - target));
      }
      if (leftSum < target && (target - leftSum) % total == 0) {
        result = Math.min(result, i + 1 + (int) ((target - leftSum) / total * nums.length));
      }
    }

    long rightSum = 0;
    for (int i = nums.length - 1; i >= 0; --i) {
      rightSum += nums[i];

      if (leftSumToLength.containsKey(target - rightSum)) {
        result = Math.min(result, nums.length - i + leftSumToLength.get(target - rightSum));
      }
      if (leftSumToLength.containsKey(target % total - rightSum)) {
        result =
            Math.min(
                result,
                nums.length
                    - i
                    + leftSumToLength.get(target % total - rightSum)
                    + (int) (target / total * nums.length));
      }
      if (target >= total && leftSumToLength.containsKey(target % total + total - rightSum)) {
        result =
            Math.min(
                result,
                nums.length
                    - i
                    + leftSumToLength.get(target % total + total - rightSum)
                    + (int) ((target / total - 1) * nums.length));
      }
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }
}
