import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int sumOfPower(int[] nums) {
    Arrays.sort(nums);

    int result = 0;
    int prev = 0;
    int factor = 0;
    for (int x : nums) {
      factor = addMod(multiplyMod(factor, 2), prev);
      result = addMod(result, multiplyMod(multiplyMod(x, x), addMod(factor, x)));

      prev = x;
    }

    return result;
  }

  static int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  static int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
