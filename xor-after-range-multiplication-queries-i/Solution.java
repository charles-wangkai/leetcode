import java.math.BigInteger;
import java.util.Arrays;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int xorAfterQueries(int[] nums, int[][] queries) {
    for (int[] query : queries) {
      for (int i = query[0]; i <= query[1]; i += query[2]) {
        nums[i] = MOD_INT.multiplyMod(nums[i], query[3]);
      }
    }

    return Arrays.stream(nums).reduce(0, (acc, x) -> acc ^ x);
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
