class Solution {
  static final int MODULUS = 1_000_000_007;

  public int monkeyMove(int n) {
    return addMod(powMod(2, n), -2);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int powMod(int base, int exponent) {
    int result = 1;
    while (exponent != 0) {
      if ((exponent & 1) == 1) {
        result = multiplyMod(result, base);
      }

      base = multiplyMod(base, base);
      exponent >>= 1;
    }

    return result;
  }
}
