import java.math.BigInteger;
import java.util.Arrays;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int numberOfStableArrays(int zero, int one, int limit) {
    int[][][] dp = new int[zero + 1][one + 1][2];
    Arrays.fill(dp[0][0], 1);

    for (int length = 1; length <= zero + one; ++length) {
      for (int c0 = Math.max(0, length - one); c0 <= Math.min(length, zero); ++c0) {
        int c1 = length - c0;

        for (int last = 1; last <= Math.min(limit, c0); ++last) {
          dp[c0][c1][0] = MOD_INT.addMod(dp[c0][c1][0], dp[c0 - last][c1][1]);
        }

        for (int last = 1; last <= Math.min(limit, c1); ++last) {
          dp[c0][c1][1] = MOD_INT.addMod(dp[c0][c1][1], dp[c0][c1 - last][0]);
        }
      }
    }

    return MOD_INT.addMod(dp[zero][one][0], dp[zero][one][1]);
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
