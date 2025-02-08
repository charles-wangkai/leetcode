import java.math.BigInteger;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int distanceSum(int m, int n, int k) {
    return multiplyMod(
        addMod(
            multiplyMod(computeDistanceSum(m), multiplyMod(n, n)),
            multiplyMod(computeDistanceSum(n), multiplyMod(m, m))),
        CMod(m * n - 2, k - 2));
  }

  int computeDistanceSum(int length) {
    return IntStream.rangeClosed(1, length - 1)
        .map(i -> multiplyMod(i, length - i))
        .reduce(this::addMod)
        .orElse(0);
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int divideMod(int x, int y) {
    return multiplyMod(x, BigInteger.valueOf(y).modInverse(BigInteger.valueOf(MODULUS)).intValue());
  }

  int CMod(int n, int r) {
    int numerator = 1;
    int denominator = 1;
    for (int i = 0; i < r; ++i) {
      numerator = multiplyMod(numerator, n - i);
      denominator = multiplyMod(denominator, i + 1);
    }

    return divideMod(numerator, denominator);
  }
}