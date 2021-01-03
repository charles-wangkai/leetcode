import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int waysToSplit(int[] nums) {
    int total = Arrays.stream(nums).sum();

    int middleMaxIndex = nums.length - 1;
    int rightSum = nums[middleMaxIndex];
    while (rightSum * 2 < total && middleMaxIndex != 0) {
      --middleMaxIndex;
      rightSum += nums[middleMaxIndex];
    }

    long result = 0;
    int leftSum = 0;
    int middleMinIndex = -1;
    int middleSum = 0;
    for (int leftIndex = 0; leftIndex < nums.length; ++leftIndex) {
      leftSum += nums[leftIndex];
      middleSum -= nums[leftIndex];

      while ((middleSum < leftSum || middleMinIndex <= leftIndex)
          && middleMinIndex + 1 != nums.length) {
        ++middleMinIndex;
        middleSum += nums[middleMinIndex];
      }

      while (middleMaxIndex != nums.length - 1
          && (rightSum - nums[middleMaxIndex]) * 2 >= total - leftSum) {
        rightSum -= nums[middleMaxIndex];
        ++middleMaxIndex;
      }

      if (leftSum <= middleSum
          && middleSum <= rightSum
          && leftIndex < middleMinIndex
          && middleMinIndex < middleMaxIndex) {
        result += middleMaxIndex - middleMinIndex;
      }
    }

    return (int) (result % MODULUS);
  }
}
