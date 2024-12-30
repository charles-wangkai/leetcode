import java.math.BigInteger;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countGoodArrays(int n, int m, int k) {
    return multiplyMod(multiplyMod(m, CMod(n - 1, k)), powMod(m - 1, n - 1 - k));
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int divideMod(int x, int y) {
    return multiplyMod(x, BigInteger.valueOf(y).modInverse(BigInteger.valueOf(MODULUS)).intValue());
  }

  int powMod(int base, int exponent) {
    int result = 1;
    for (int i = 0; i < exponent; ++i) {
      result = multiplyMod(result, base);
    }

    return result;
  }

  int factorialMod(int x) {
    int result = 1;
    for (int i = 1; i <= x; ++i) {
      result = multiplyMod(result, i);
    }

    return result;
  }

  int CMod(int n, int r) {
    return divideMod(factorialMod(n), multiplyMod(factorialMod(r), factorialMod(n - r)));
  }
}
