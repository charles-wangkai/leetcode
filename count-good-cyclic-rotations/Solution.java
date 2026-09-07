import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int countGoodRotations(int[] nums) {
    int n = nums.length;

    long total = Arrays.stream(nums).asLongStream().sum();

    int result = 0;
    long sum = IntStream.range(0, n / 2 - 1).map(i -> nums[i]).asLongStream().sum();
    for (int i = 0; i < nums.length; ++i) {
      sum += nums[(i + n / 2 - 1) % nums.length];

      if (sum > total - sum) {
        ++result;
      }

      sum -= nums[i];
    }

    return result;
  }
}