import java.util.Arrays;

class Solution {
  public int waysToSplitArray(int[] nums) {
    int result = 0;
    long leftSum = 0;
    long rightSum = Arrays.stream(nums).asLongStream().sum();
    for (int i = 0; i < nums.length - 1; ++i) {
      leftSum += nums[i];
      rightSum -= nums[i];
      if (leftSum >= rightSum) {
        ++result;
      }
    }

    return result;
  }
}