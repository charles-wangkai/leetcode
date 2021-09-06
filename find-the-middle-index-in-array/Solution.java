import java.util.Arrays;

class Solution {
  public int findMiddleIndex(int[] nums) {
    int leftSum = 0;
    int rightSum = Arrays.stream(nums).sum();
    for (int i = 0; i < nums.length; ++i) {
      rightSum -= nums[i];
      if (leftSum == rightSum) {
        return i;
      }

      leftSum += nums[i];
    }

    return -1;
  }
}
