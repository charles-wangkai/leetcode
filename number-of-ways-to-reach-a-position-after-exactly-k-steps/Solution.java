import java.math.BigInteger;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numberOfWays(int startPos, int endPos, int k) {
    int diff = Math.abs(startPos - endPos);

    return (k < diff || (k - diff) % 2 != 0) ? 0 : CMod(k, (k - diff) / 2);
  }

  int CMod(int n, int r) {
    int result = 1;
    for (int i = 0; i < r; ++i) {
      result = multiplyMod(result, multiplyMod(n - i, modInv(i + 1)));
    }

    return result;
  }

  static int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  static int modInv(int x) {
    return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(MODULUS)).intValue();
  }
}