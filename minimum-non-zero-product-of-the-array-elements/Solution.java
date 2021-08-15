class Solution {
  static final int MODULUS = 1_000_000_007;

  public int minNonZeroProduct(int p) {
    return multiplyMod(mod((1L << p) - 1), powMod(mod((1L << p) - 2), (1L << (p - 1)) - 1));
  }

  static int multiplyMod(int x, int y) {
    return mod((long) x * y);
  }

  static int powMod(int base, long exponent) {
    int result = 1;
    while (exponent != 0) {
      if ((exponent & 1) != 0) {
        result = multiplyMod(result, base);
      }

      base = multiplyMod(base, base);
      exponent >>= 1;
    }

    return result;
  }

  static int mod(long x) {
    return (int) (x % MODULUS);
  }
}
