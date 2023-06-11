import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int sumDistance(int[] nums, String s, int d) {
    long[] positions =
        IntStream.range(0, nums.length)
            .mapToLong(i -> (long) nums[i] + ((s.charAt(i) == 'L') ? -1 : 1) * d)
            .sorted()
            .toArray();

    int result = 0;
    long rightSum = Arrays.stream(positions).sum();
    for (int i = 0; i < positions.length; ++i) {
      rightSum -= positions[i];
      result =
          addMod(
              result,
              addMod(mod(rightSum), -multiplyMod(mod(positions[i]), positions.length - i - 1)));
    }

    return result;
  }

  int mod(long x) {
    return (int) (x % MODULUS);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
