import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int BASE = 31;
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int[] countSuffix(int[] nums, int[] target) {
    int n = nums.length;
    int m = target.length;

    int[] basePowers = buildBasePowers(n);

    int[] valuePrefixHashes = buildPrefixHashes(nums);
    int[] targetPrefixHashes = buildPrefixHashes(target);

    Map<Integer, Integer> valueToDiff = new HashMap<>();
    for (int num : nums) {
      updateMap(valueToDiff, num, -1);
    }

    int lastIndex = -1;
    for (int i = 0; i < n - 1; ++i) {
      updateMap(valueToDiff, target[i], 1);

      if (i == 0 || target[i - 1] < target[i]) {
        lastIndex = i;
      }
    }

    int[] result = new int[m - n + 1];
    for (int i = 0; i < result.length; ++i) {
      int rightIndex = i + n - 1;

      updateMap(valueToDiff, target[rightIndex], 1);

      if (rightIndex == 0 || target[rightIndex - 1] < target[rightIndex]) {
        lastIndex = rightIndex;
      }

      if (valueToDiff.isEmpty()) {
        int beginIndex = Math.max(0, lastIndex - i);

        result[i] =
            Math.max(
                0,
                findEndIndex(basePowers, valuePrefixHashes, targetPrefixHashes, i)
                    - beginIndex
                    + 1);
      }

      updateMap(valueToDiff, target[i], -1);
    }

    return result;
  }

  int[] buildBasePowers(int n) {
    int[] result = new int[n + 1];
    result[0] = 1;

    for (int i = 1; i < result.length; ++i) {
      result[i] = MOD_INT.multiplyMod(result[i - 1], BASE);
    }

    return result;
  }

  int[] buildPrefixHashes(int[] a) {
    int[] result = new int[a.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] = MOD_INT.addMod(MOD_INT.multiplyMod((i == 0) ? 0 : result[i - 1], BASE), a[i]);
    }

    return result;
  }

  int computeRangeHash(int[] basePowers, int[] prefixHashes, int fromIndex, int toIndex) {
    return MOD_INT.addMod(
        prefixHashes[toIndex],
        -MOD_INT.multiplyMod(
            (fromIndex == 0) ? 0 : prefixHashes[fromIndex - 1],
            basePowers[toIndex - fromIndex + 1]));
  }

  int findEndIndex(
      int[] basePowers, int[] valuePrefixHashes, int[] targetPrefixHashes, int beginIndex) {
    int n = valuePrefixHashes.length;

    int result = n - 1;
    int lower = 0;
    int upper = n - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (computeRangeHash(basePowers, valuePrefixHashes, 0, middle)
          != computeRangeHash(basePowers, targetPrefixHashes, beginIndex, beginIndex + middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> valueToDiff, int value, int delta) {
    valueToDiff.put(value, valueToDiff.getOrDefault(value, 0) + delta);
    valueToDiff.remove(value, 0);
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
