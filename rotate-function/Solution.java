import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int maxRotateFunction(int[] nums) {
    int n = nums.length;

    int left = 0;
    int leftSum = 0;

    int right = IntStream.range(0, n).map(i -> i * nums[i]).sum();
    int rightSum = Arrays.stream(nums).sum();

    int result = left + right;
    for (int i = 0; i < n - 1; ++i) {
      left += leftSum;
      leftSum += nums[n - 1 - i];

      right += rightSum - n * nums[n - 1 - i];
      rightSum -= nums[n - 1 - i];

      result = Math.max(result, left + right);
    }

    return result;
  }
}
