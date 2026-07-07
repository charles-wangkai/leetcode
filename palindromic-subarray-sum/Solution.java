import java.math.BigInteger;
import java.util.stream.IntStream;

class Solution {
  static final int BASE = 31;
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public long getSum(int[] nums) {
    long[] prefixSums = new long[nums.length + 1];
    for (int i = 1; i < prefixSums.length; ++i) {
      prefixSums[i] = prefixSums[i - 1] + nums[i - 1];
    }

    int[] basePowers = new int[nums.length + 1];
    basePowers[0] = 1;
    for (int i = 1; i < basePowers.length; ++i) {
      basePowers[i] = MOD_INT.multiplyMod(basePowers[i - 1], BASE);
    }

    int[] forwardPrefixHashes = buildPrefixHashes(nums);
    int[] backwardPrefixHashes =
        buildPrefixHashes(
            IntStream.range(0, nums.length).map(i -> nums[nums.length - 1 - i]).toArray());

    long result = Long.MIN_VALUE;
    for (int offset = 0; offset <= 1; ++offset) {
      for (int middleLeftIndex = 0; middleLeftIndex + offset < nums.length; ++middleLeftIndex) {
        int middleRightIndex = middleLeftIndex + offset;
        if (nums[middleLeftIndex] == nums[middleRightIndex]) {
          int delta =
              findDelta(
                  nums.length,
                  basePowers,
                  forwardPrefixHashes,
                  backwardPrefixHashes,
                  middleLeftIndex,
                  middleRightIndex);

          result =
              Math.max(
                  result,
                  prefixSums[middleRightIndex + delta + 1] - prefixSums[middleLeftIndex - delta]);
        }
      }
    }

    return result;
  }

  int findDelta(
      int length,
      int[] basePowers,
      int[] forwardPrefixHashes,
      int[] backwardPrefixHashes,
      int middleLeftIndex,
      int middleRightIndex) {
    int result = -1;
    int lower = 0;
    int upper = Math.min(middleLeftIndex, length - 1 - middleRightIndex);
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (computeRangeHash(
              basePowers, forwardPrefixHashes, middleLeftIndex - middle, middleLeftIndex)
          == computeRangeHash(
              basePowers,
              backwardPrefixHashes,
              length - 1 - (middleRightIndex + middle),
              length - 1 - middleRightIndex)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  int computeRangeHash(int[] basePowers, int[] prefixHashes, int beginIndex, int endIndex) {
    return MOD_INT.addMod(
        prefixHashes[endIndex + 1],
        -MOD_INT.multiplyMod(prefixHashes[beginIndex], basePowers[endIndex - beginIndex + 1]));
  }

  int[] buildPrefixHashes(int[] values) {
    int[] result = new int[values.length + 1];
    for (int i = 1; i < result.length; ++i) {
      result[i] = MOD_INT.addMod(MOD_INT.multiplyMod(result[i - 1], BASE), values[i - 1]);
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
