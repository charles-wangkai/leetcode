import java.math.BigInteger;
import java.util.Arrays;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int zigZagArrays(int n, int l, int r) {
    int[][] dp = new int[r + 1][2];
    for (int value = l; value <= r; ++value) {
      dp[value][0] = 1;
      dp[value][1] = 1;
    }

    int[][] nextDp = new int[r + 1][2];
    for (int i = 0; i < n - 1; ++i) {
      for (int value = 0; value < nextDp.length; ++value) {
        Arrays.fill(nextDp[value], 0);
      }

      int upSum = 0;
      for (int nextValue = l; nextValue <= r; ++nextValue) {
        nextDp[nextValue][1] = MOD_INT.addMod(nextDp[nextValue][1], upSum);
        upSum = MOD_INT.addMod(upSum, dp[nextValue][0]);
      }

      int downSum = 0;
      for (int nextValue = r; nextValue >= l; --nextValue) {
        nextDp[nextValue][0] = MOD_INT.addMod(nextDp[nextValue][0], downSum);
        downSum = MOD_INT.addMod(downSum, dp[nextValue][1]);
      }

      int[][] temp = dp;
      dp = nextDp;
      nextDp = temp;
    }

    int result = 0;
    for (int value = 0; value < dp.length; ++value) {
      for (int d = 0; d < dp[value].length; ++d) {
        result = MOD_INT.addMod(result, dp[value][d]);
      }
    }

    return result;
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
