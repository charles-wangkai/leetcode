import java.math.BigInteger;
import java.util.Arrays;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int[] sumAndMultiply(String s, int[][] queries) {
    int m = s.length();

    int[] prefixDigitSum = new int[m + 1];
    int[] prefixNonZeroCounts = new int[m + 1];
    int[] prefixCompressed = new int[m + 1];
    for (int i = 1; i <= m; ++i) {
      int digit = s.charAt(i - 1) - '0';

      prefixDigitSum[i] = prefixDigitSum[i - 1] + digit;
      prefixNonZeroCounts[i] = prefixNonZeroCounts[i - 1] + ((digit == 0) ? 0 : 1);
      prefixCompressed[i] =
          (digit == 0)
              ? prefixCompressed[i - 1]
              : MOD_INT.addMod(MOD_INT.multiplyMod(prefixCompressed[i - 1], 10), digit);
    }

    int[] tenPowers = new int[m + 1];
    tenPowers[0] = 1;
    for (int i = 1; i < tenPowers.length; ++i) {
      tenPowers[i] = MOD_INT.multiplyMod(tenPowers[i - 1], 10);
    }

    return Arrays.stream(queries)
        .mapToInt(
            query ->
                MOD_INT.multiplyMod(
                    MOD_INT.addMod(
                        prefixCompressed[query[1] + 1],
                        -MOD_INT.multiplyMod(
                            prefixCompressed[query[0]],
                            tenPowers[
                                prefixNonZeroCounts[query[1] + 1]
                                    - prefixNonZeroCounts[query[0]]])),
                    prefixDigitSum[query[1] + 1] - prefixDigitSum[query[0]]))
        .toArray();
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
