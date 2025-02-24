import java.math.BigInteger;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 10;

  public boolean hasSameDigits(String s) {
    int[] products = new int[s.length() - 1];
    int[] count2s = new int[s.length() - 1];
    int[] count5s = new int[s.length() - 1];
    precompute(products, count2s, count5s);

    int[] coeffs =
        IntStream.range(0, s.length() - 1)
            .map(i -> CMod(products, count2s, count5s, s.length() - 2, i))
            .toArray();

    return computeDigit(coeffs, s, 0) == computeDigit(coeffs, s, 1);
  }

  void precompute(int[] products, int[] count2s, int[] count5s) {
    products[0] = 1;
    for (int i = 1; i < products.length; ++i) {
      count2s[i] = count2s[i - 1];
      count5s[i] = count5s[i - 1];

      int rest = i;
      while (rest % 2 == 0) {
        ++count2s[i];
        rest /= 2;
      }
      while (rest % 5 == 0) {
        ++count5s[i];
        rest /= 5;
      }

      products[i] = multiplyMod(products[i - 1], rest);
    }
  }

  int computeDigit(int[] coeffs, String s, int beginIndex) {
    return IntStream.range(0, coeffs.length)
        .map(i -> multiplyMod(coeffs[i], s.charAt(beginIndex + i) - '0'))
        .reduce(this::addMod)
        .getAsInt();
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }

  int multiplyMod(int x, int y) {
    return x * y % MODULUS;
  }

  int divideMod(int x, int y) {
    return multiplyMod(x, BigInteger.valueOf(y).modInverse(BigInteger.valueOf(MODULUS)).intValue());
  }

  int powMod(int base, int exponent) {
    return (exponent == 0)
        ? 1
        : multiplyMod(
            (exponent % 2 == 1) ? base : 1, powMod(multiplyMod(base, base), exponent / 2));
  }

  int CMod(int[] products, int[] count2s, int[] count5s, int n, int r) {
    int rest2 = count2s[n] - count2s[r] - count2s[n - r];
    int rest5 = count5s[n] - count5s[r] - count5s[n - r];
    if (rest2 != 0 && rest5 != 0) {
      return 0;
    }

    return multiplyMod(
        divideMod(products[n], multiplyMod(products[r], products[n - r])),
        multiplyMod(powMod(2, rest2), powMod(5, rest5)));
  }
}