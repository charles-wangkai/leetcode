import java.util.Arrays;

class Solution {
  public long maximumSumScore(int[] nums) {
    long result = Long.MIN_VALUE;
    long leftSum = 0;
    long rightSum = Arrays.stream(nums).asLongStream().sum();
    for (int i = 0; i < nums.length; ++i) {
      leftSum += nums[i];
      result = Math.max(result, Math.max(leftSum, rightSum));
      rightSum -= nums[i];
    }

    return result;
  }
}
