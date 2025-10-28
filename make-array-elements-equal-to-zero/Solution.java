import java.util.Arrays;

class Solution {
  public int countValidSelections(int[] nums) {
    int result = 0;
    int leftSum = 0;
    int rightSum = Arrays.stream(nums).sum();
    for (int num : nums) {
      if (num == 0) {
        if (leftSum == rightSum) {
          result += 2;
        } else if (Math.abs(leftSum - rightSum) == 1) {
          ++result;
        }
      }

      leftSum += num;
      rightSum -= num;
    }

    return result;
  }
}