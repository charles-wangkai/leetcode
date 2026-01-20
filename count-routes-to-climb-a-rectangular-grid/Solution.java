import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int numberOfRoutes(String[] grid, int d) {
    int n = grid.length;
    int m = grid[0].length();

    int[] dp =
        move(
            IntStream.range(0, m).map(c -> (grid[n - 1].charAt(c) == '.') ? 1 : 0).toArray(),
            grid[n - 1],
            d);
    for (int r = n - 2; r >= 0; --r) {
      dp = move(move(dp, grid[r], d - 1), grid[r], d);
    }

    return Arrays.stream(dp).reduce(MOD_INT::addMod).getAsInt();
  }

  int[] move(int[] dp, String line, int maxDelta) {
    int[] prefixSums = new int[dp.length + 1];
    for (int i = 1; i < prefixSums.length; ++i) {
      prefixSums[i] = MOD_INT.addMod(prefixSums[i - 1], dp[i - 1]);
    }

    int[] result = new int[dp.length];
    for (int c = 0; c < result.length; ++c) {
      if (line.charAt(c) == '.') {
        int beginIndex = Math.max(0, c - maxDelta);
        int endIndex = Math.min(dp.length - 1, c + maxDelta);

        result[c] = MOD_INT.addMod(prefixSums[endIndex + 1], -prefixSums[beginIndex]);
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
    return (int) (x % modulus);
  }

  int modInv(int x) {
    return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(modulus)).intValue();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, modulus);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, modulus);
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
