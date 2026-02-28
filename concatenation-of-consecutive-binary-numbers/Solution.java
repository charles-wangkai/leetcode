import java.math.BigInteger;
import java.util.stream.IntStream;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int concatenatedBinary(int n) {
    return IntStream.rangeClosed(1, n)
        .reduce(
            0,
            (acc, x) -> MOD_INT.addMod(MOD_INT.multiplyMod(acc, Integer.highestOneBit(x) * 2), x));
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
