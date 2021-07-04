class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countGoodNumbers(long n) {
    long evenCount = (n + 1) / 2;
    long oddCount = n - evenCount;

    return multiplyMod(powMod(5, evenCount), powMod(4, oddCount));
  }

  int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }

  int powMod(int base, long exponent) {
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
}
