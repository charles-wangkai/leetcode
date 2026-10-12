import java.math.BigInteger;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  static final int BIT_NUM = 30;
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int maxSum(List<Integer> nums, int k) {
    int[] values = new int[nums.size()];
    for (int b = 0; b < BIT_NUM; ++b) {
      int b_ = b;
      int oneCount = (int) nums.stream().filter(x -> ((x >> b_) & 1) == 1).count();

      for (int i = 0; i < oneCount; ++i) {
        values[i] |= 1 << b;
      }
    }

    return IntStream.range(0, k)
        .map(i -> MOD_INT.multiplyMod(values[i], values[i]))
        .reduce(MOD_INT::addMod)
        .getAsInt();
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
