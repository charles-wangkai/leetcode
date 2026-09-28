import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int longestSubarray(int[] nums, int k) {
    ModInt modInt = new ModInt(k);

    Map<Integer, Integer> prefixSumToMinLength = new HashMap<>();
    prefixSumToMinLength.put(0, 0);

    Map<Integer, Integer> prefixSumToMaxLength = new HashMap<>();
    prefixSumToMaxLength.put(0, 0);

    int prefixSum = 0;
    for (int i = 0; i < nums.length; ++i) {
      prefixSum = modInt.addMod(prefixSum, nums[i]);

      if (!prefixSumToMinLength.containsKey(prefixSum)) {
        prefixSumToMinLength.put(prefixSum, i + 1);
      }

      prefixSumToMaxLength.put(prefixSum, i + 1);
    }

    int result =
        prefixSumToMinLength.keySet().stream()
            .mapToInt(ps -> prefixSumToMaxLength.get(ps) - prefixSumToMinLength.get(ps))
            .max()
            .getAsInt();

    prefixSum = 0;
    Map<Integer, Integer> doubleRemainderToMaxLength = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      doubleRemainderToMaxLength.put(modInt.multiplyMod(nums[i], 2), i + 1);

      prefixSum = modInt.addMod(prefixSum, nums[i]);

      if (prefixSumToMaxLength.get(prefixSum) == i + 1) {
        for (int doubleRemainder : doubleRemainderToMaxLength.keySet()) {
          int target = modInt.addMod(prefixSum, -doubleRemainder);
          if (prefixSumToMinLength.containsKey(target)
              && prefixSumToMinLength.get(target)
                  < doubleRemainderToMaxLength.get(doubleRemainder)) {
            result = Math.max(result, i + 1 - prefixSumToMinLength.get(target));
          }
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
