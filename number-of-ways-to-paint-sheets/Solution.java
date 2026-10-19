import java.math.BigInteger;
import java.util.Arrays;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int numberOfWays(int n, int[] limit) {
    int[] sorted = Arrays.stream(limit).map(l -> Math.min(n - 1, l)).sorted().toArray();

    int result = 0;
    int beginIndex = sorted.length;
    int suffixSum = 0;
    for (int i = 0; i < sorted.length; ++i) {
      while (beginIndex != 0 && sorted[i] + sorted[beginIndex - 1] >= n) {
        --beginIndex;
        suffixSum = MOD_INT.addMod(suffixSum, sorted[beginIndex]);
      }

      int pairNum = sorted.length - beginIndex;
      int otherSum = suffixSum;
      if (i >= beginIndex) {
        --pairNum;
        otherSum = MOD_INT.addMod(otherSum, -sorted[i]);
      }

      result =
          MOD_INT.addMod(
              result, MOD_INT.addMod(MOD_INT.multiplyMod(sorted[i] - n + 1, pairNum), otherSum));
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
