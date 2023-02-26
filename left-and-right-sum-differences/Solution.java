import java.util.Arrays;

class Solution {
  public int[] leftRigthDifference(int[] nums) {
    int[] result = new int[nums.length];
    int leftSum = 0;
    int rightSum = Arrays.stream(nums).sum();
    for (int i = 0; i < result.length; ++i) {
      rightSum -= nums[i];
      result[i] = Math.abs(leftSum - rightSum);
      leftSum += nums[i];
    }

    return result;
  }
}
