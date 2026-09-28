import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

class Solution {
  public int longestSubarray(int[] nums, int k) {
    ModInt modInt = new ModInt(k);

    int result = 0;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      Set<Integer> doubleRemainders = new HashSet<>();
      int sumRemainder = 0;
      for (int endIndex = beginIndex; endIndex < nums.length; ++endIndex) {
        doubleRemainders.add(modInt.multiplyMod(nums[endIndex], 2));
        sumRemainder = modInt.addMod(sumRemainder, nums[endIndex]);

        if (sumRemainder == 0 || doubleRemainders.contains(sumRemainder)) {
          result = Math.max(result, endIndex - beginIndex + 1);
        }
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
