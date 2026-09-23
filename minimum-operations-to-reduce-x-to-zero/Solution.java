import java.util.Arrays;

class Solution {
  public int minOperations(int[] nums, int x) {
    if (Arrays.stream(nums).sum() < x) {
      return -1;
    }

    int minOperationNum = Integer.MAX_VALUE;
    int leftSum = 0;
    int rightIndex = 0;
    int rightSum = Arrays.stream(nums).sum();
    for (int leftIndex = -1; leftIndex < nums.length; ++leftIndex) {
      if (leftIndex != -1) {
        leftSum += nums[leftIndex];
      }

      while (rightIndex != nums.length && leftSum + (rightSum - nums[rightIndex]) >= x) {
        rightSum -= nums[rightIndex];
        ++rightIndex;
      }

      if (leftSum + rightSum == x) {
        minOperationNum = Math.min(minOperationNum, (leftIndex + 1) + (nums.length - rightIndex));
      }
    }

    return (minOperationNum == Integer.MAX_VALUE) ? -1 : minOperationNum;
  }
}
