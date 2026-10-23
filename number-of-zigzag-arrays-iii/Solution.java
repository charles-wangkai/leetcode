// https://chatgpt.com/share/6adaec0a-d298-83ec-a93b-3ccb204f86d8

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int zigZagArrays(int n, int l, int r) {
    int result = 0;
    for (int i = 1; i <= n + 1; ++i) {
      int term = computeWayNum(n, i);
      for (int j = 1; j <= n + 1; ++j) {
        if (j != i) {
          term = MOD_INT.multiplyMod(term, MOD_INT.divideMod((r - l + 1) - j, i - j));
        }
      }

      result = MOD_INT.addMod(result, term);
    }

    return result;
  }

  int computeWayNum(int n, int m) {
    int[] upDp = new int[m];
    Arrays.fill(upDp, 1);

    int[] downDp = new int[m];
    Arrays.fill(downDp, 1);

    for (int p = 0; p < n - 1; ++p) {
      int[] nextUpDp = new int[m];
      int[] nextDownDp = new int[m];

      int prefixSum = 0;
      for (int i = 0; i < nextUpDp.length; ++i) {
        nextUpDp[i] = prefixSum;
        prefixSum = MOD_INT.addMod(prefixSum, downDp[i]);
      }

      int suffixSum = 0;
      for (int i = nextDownDp.length - 1; i >= 0; --i) {
        nextDownDp[i] = suffixSum;
        suffixSum = MOD_INT.addMod(suffixSum, upDp[i]);
      }

      upDp = nextUpDp;
      downDp = nextDownDp;
    }

    return IntStream.concat(Arrays.stream(upDp), Arrays.stream(downDp)).reduce(0, MOD_INT::addMod);
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
