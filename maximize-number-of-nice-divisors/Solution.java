class Solution {
  static final int MODULUS = 1_000_000_007;

  public int maxNiceDivisors(int primeFactors) {
    if (primeFactors == 1) {
      return 1;
    }

    if (primeFactors % 3 == 0) {
      return powMod(3, primeFactors / 3);
    } else if (primeFactors % 3 == 1) {
      return multiplyMod(powMod(3, primeFactors / 3 - 1), 4);
    }

    return multiplyMod(powMod(3, primeFactors / 3), 2);
  }

  int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }

  int powMod(int base, int exponent) {
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
