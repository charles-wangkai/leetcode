import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int BASE = 100003;
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int smallestUniqueSubarray(int[] nums) {
    int result = -1;
    int lower = 1;
    int upper = nums.length;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(nums, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] nums, int length) {
    int hash = 0;
    for (int i = 0; i < length - 1; ++i) {
      hash = MOD_INT.addMod(MOD_INT.multiplyMod(hash, BASE), nums[i]);
    }

    int basePower = MOD_INT.powMod(BASE, length - 1);

    Map<Integer, Integer> hashToCount = new HashMap<>();
    for (int i = length - 1; i < nums.length; ++i) {
      hash = MOD_INT.addMod(MOD_INT.multiplyMod(hash, BASE), nums[i]);

      hashToCount.put(hash, hashToCount.getOrDefault(hash, 0) + 1);

      hash = MOD_INT.addMod(hash, -MOD_INT.multiplyMod(nums[i - length + 1], basePower));
    }

    return hashToCount.values().stream().anyMatch(count -> count == 1);
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
