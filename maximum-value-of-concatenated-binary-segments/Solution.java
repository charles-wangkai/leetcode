import java.math.BigInteger;
import java.util.stream.IntStream;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int maxValue(int[] nums1, int[] nums0) {
    String[] strs =
        IntStream.range(0, nums1.length)
            .mapToObj(i -> "1".repeat(nums1[i]) + "0".repeat(nums0[i]))
            .sorted((s1, s2) -> -(s1 + s2).compareTo(s2 + s1))
            .toArray(String[]::new);

    int result = 0;
    for (String str : strs) {
      for (char c : str.toCharArray()) {
        result = MOD_INT.addMod(MOD_INT.multiplyMod(result, 2), c - '0');
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
