import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int LIMIT = 30;
  static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
  static final int[] MASKS = buildMasks();
  static final int MODULUS = 1_000_000_007;

  static int[] buildMasks() {
    int[] masks = new int[LIMIT + 1];
    for (int i = 0; i < masks.length; ++i) {
      int mask = 0;
      for (int j = 0; j < PRIMES.length; ++j) {
        if (i % (PRIMES[j] * PRIMES[j]) == 0) {
          mask = -1;

          break;
        }

        if (i % PRIMES[j] == 0) {
          mask |= 1 << j;
        }
      }

      masks[i] = mask;
    }

    return masks;
  }

  public int numberOfGoodSubsets(int[] nums) {
    int[] counts = new int[LIMIT + 1];
    for (int num : nums) {
      ++counts[num];
    }

    int[] wayNums = new int[1 << PRIMES.length];
    wayNums[0] = 1;

    for (int i = 2; i < MASKS.length; ++i) {
      if (MASKS[i] != -1) {
        int[] nextWayNums = Arrays.copyOf(wayNums, wayNums.length);
        for (int j = 0; j < nextWayNums.length; ++j) {
          if ((j | MASKS[i]) == j) {
            nextWayNums[j] = addMod(nextWayNums[j], multiplyMod(counts[i], wayNums[j - MASKS[i]]));
          }
        }

        wayNums = nextWayNums;
      }
    }

    int[] wayNums_ = wayNums;
    return multiplyMod(
        powMod(2, counts[1]),
        IntStream.range(1, wayNums.length).map(i -> wayNums_[i]).reduce(this::addMod).getAsInt());
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }

  int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }

  int powMod(int base, int exponent) {
    int result = 1;
    for (int i = 0; i < exponent; ++i) {
      result = multiplyMod(result, base);
    }

    return result;
  }
}
