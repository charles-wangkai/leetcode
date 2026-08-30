import java.math.BigInteger;
import java.util.Arrays;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int sumDecoded(long[] nums) {
    return Arrays.stream(nums)
        .mapToInt(
            value -> {
              int width = (int) (value % 10);
              String s = String.valueOf(value / 10);
              int x = Integer.parseInt(s.substring(0, width));
              long y = Long.parseLong(s.substring(width));

              return MOD_INT.powMod(x, y);
            })
        .reduce(0, MOD_INT::addMod);
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
