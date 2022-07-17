import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minOperations(int[] nums, int[] numsDivide) {
    int g = Arrays.stream(numsDivide).reduce(this::gcd).getAsInt();

    Arrays.sort(nums);

    return IntStream.range(0, nums.length).filter(i -> g % nums[i] == 0).findFirst().orElse(-1);
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}