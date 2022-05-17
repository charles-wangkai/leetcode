import java.util.Arrays;

class Solution {
  public int waysToSplitArray(int[] nums) {
    long total = Arrays.stream(nums).asLongStream().sum();

    int result = 0;
    long leftSum = 0;
    for (int i = 0; i < nums.length - 1; ++i) {
      leftSum += nums[i];
      long rightSum = total - leftSum;
      if (leftSum >= rightSum) {
        ++result;
      }
    }

    return result;
  }
}