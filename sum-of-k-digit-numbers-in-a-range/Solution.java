import java.math.BigInteger;
import java.util.stream.IntStream;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int sumOfNumbers(int l, int r, int k) {
    return MOD_INT.multiplyMod(
        MOD_INT.multiplyMod(IntStream.rangeClosed(l, r).sum(), MOD_INT.powMod(r - l + 1, k - 1)),
        MOD_INT.divideMod(MOD_INT.addMod(MOD_INT.powMod(10, k), -1), 9));
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
