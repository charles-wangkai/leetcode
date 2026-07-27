import java.math.BigInteger;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int countValidSequences(int n, int k) {
    int result = CMod(n - 1, n - k);

    if ((n - k) % 2 == 0) {
      result = MOD_INT.addMod(result, -CMod(k - 1 + (n - k) / 2, (n - k) / 2));
    }

    return result;
  }

  int CMod(int n, int r) {
    int numer = 1;
    int denom = 1;
    for (int i = 0; i < r; ++i) {
      numer = MOD_INT.multiplyMod(numer, n - i);
      denom = MOD_INT.multiplyMod(denom, i + 1);
    }

    return MOD_INT.divideMod(numer, denom);
  }
}

class ModInt {
  int modulus;

  ModInt(int modulus) {
    this.modulus = modulus;
  }

  int mod(long x) {
    return Math.floorMod(x, modulus);
  }

  int modInv(int x) {
    return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(modulus)).intValue();
  }

  int addMod(int x, int y) {
    return mod(x + y);
  }

  int multiplyMod(int x, int y) {
    return mod((long) x * y);
  }

  int divideMod(int x, int y) {
    return multiplyMod(x, modInv(y));
  }

  int powMod(int base, long exponent) {
    if (exponent == 0) {
      return 1;
    }

    return multiplyMod(
        (exponent % 2 == 0) ? 1 : base, powMod(multiplyMod(base, base), exponent / 2));
  }
}
