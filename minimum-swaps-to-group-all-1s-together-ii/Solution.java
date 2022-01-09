import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minSwaps(int[] nums) {
    int oneCount = (int) Arrays.stream(nums).filter(num -> num == 1).count();
    if (oneCount <= 1) {
      return 0;
    }

    int result = Integer.MAX_VALUE;
    int sum = IntStream.range(0, oneCount - 1).map(i -> nums[i]).sum();
    for (int i = 0; i < nums.length; ++i) {
      sum += nums[(i + oneCount - 1) % nums.length];
      result = Math.min(result, oneCount - sum);

      sum -= nums[i];
    }

    return result;
  }
}