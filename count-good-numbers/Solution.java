class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countGoodNumbers(long n) {
    return multiplyMod(powMod(5, (n + 1) / 2), powMod(4, n / 2));
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int powMod(int base, long exponent) {
    if (exponent == 0) {
      return 1;
    }

    return multiplyMod(
        powMod(multiplyMod(base, base), exponent / 2), (exponent % 2 == 0) ? 1 : base);
  }
}
