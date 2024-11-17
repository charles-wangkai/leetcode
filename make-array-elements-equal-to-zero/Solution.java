import java.util.Arrays;

class Solution {
  public int countValidSelections(int[] nums) {
    int total = Arrays.stream(nums).sum();

    int result = 0;
    int leftSum = 0;
    for (int num : nums) {
      if (num == 0) {
        int rightSum = total - leftSum;
        if (leftSum == rightSum) {
          result += 2;
        }
        if (Math.abs(leftSum - rightSum) == 1) {
          ++result;
        }
      }

      leftSum += num;
    }

    return result;
  }
}