import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int sumOfFlooredPairs(int[] nums) {
    int[] counts = new int[Arrays.stream(nums).max().getAsInt() + 1];
    for (int num : nums) {
      ++counts[num];
    }

    int[] prefixSums = new int[counts.length];
    for (int i = 0; i < prefixSums.length; ++i) {
      prefixSums[i] = ((i == 0) ? 0 : prefixSums[i - 1]) + counts[i];
    }

    int result = 0;
    for (int i = 1; i < counts.length; ++i) {
      for (int j = i; j < counts.length; j += i) {
        result =
            addMod(
                result,
                multiplyMod(
                    computeRangeSum(prefixSums, j, Math.min(prefixSums.length - 1, j + i - 1)),
                    multiplyMod(j / i, counts[i])));
      }
    }

    return result;
  }

  static int computeRangeSum(int[] prefixSums, int begin, int end) {
    return subtractMod(prefixSums[end], (begin == 0) ? 0 : prefixSums[begin - 1]);
  }

  static int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }

  static int subtractMod(int x, int y) {
    return (x - y + MODULUS) % MODULUS;
  }

  static int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }
}
