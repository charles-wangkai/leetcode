import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int LIMIT = 5000;
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int countArrays(int[] digitSum) {
    int[] digitSums = IntStream.rangeClosed(0, LIMIT).map(this::computeDigitSum).toArray();

    int[] dp =
        IntStream.range(0, digitSums.length)
            .map(x -> (digitSums[x] == digitSum[0]) ? 1 : 0)
            .toArray();
    for (int i = 1; i < digitSum.length; ++i) {
      int[] nextDp = new int[dp.length];
      int prefixSum = 0;
      for (int curr = 0; curr < nextDp.length; ++curr) {
        prefixSum = MOD_INT.addMod(prefixSum, dp[curr]);

        if (digitSums[curr] == digitSum[i]) {
          nextDp[curr] = prefixSum;
        }
      }

      dp = nextDp;
    }

    return Arrays.stream(dp).reduce(0, MOD_INT::addMod);
  }

  int computeDigitSum(int x) {
    return String.valueOf(x).chars().map(c -> c - '0').sum();
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
