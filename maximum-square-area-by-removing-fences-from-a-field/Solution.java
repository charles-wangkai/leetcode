import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
    Set<Integer> hSizes = buildSizes(m, hFences);
    OptionalInt maxSize =
        buildSizes(n, vFences).stream().filter(hSizes::contains).mapToInt(Integer::intValue).max();

    return maxSize.isPresent() ? MOD_INT.powMod(maxSize.getAsInt(), 2) : -1;
  }

  Set<Integer> buildSizes(int length, int[] fences) {
    int[] values =
        IntStream.concat(IntStream.of(1, length), Arrays.stream(fences)).sorted().toArray();

    Set<Integer> result = new HashSet<>();
    for (int i = 0; i < values.length; ++i) {
      for (int j = i + 1; j < values.length; ++j) {
        result.add(values[j] - values[i]);
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
