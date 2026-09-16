import java.math.BigInteger;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int numberOfSets(int n, int k) {
    int[][] dp = new int[k + 1][2];
    dp[0][0] = 1;

    for (int i = 0; i < n - 1; ++i) {
      int[][] nextDp = new int[k + 1][2];
      for (int segmentNum = 0; segmentNum <= k; ++segmentNum) {
        nextDp[segmentNum][0] = MOD_INT.addMod(dp[segmentNum][0], dp[segmentNum][1]);

        if (segmentNum != 0) {
          nextDp[segmentNum][1] =
              MOD_INT.addMod(
                  MOD_INT.addMod(dp[segmentNum - 1][0], dp[segmentNum - 1][1]), dp[segmentNum][1]);
        }
      }

      dp = nextDp;
    }

    return MOD_INT.addMod(dp[k][0], dp[k][1]);
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
