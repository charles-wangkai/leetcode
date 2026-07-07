import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  static final ModInt MOD_INT = new ModInt(1_000_000_007);

  public int divisibleGame(int[] nums) {
    int maxScoreDiff;
    int bestK;
    if (Arrays.stream(nums).allMatch(num -> num == 1)) {
      maxScoreDiff = -1;
      bestK = 2;
    } else {
      int[] primeFactors =
          Arrays.stream(nums)
              .flatMap(num -> buildPrimeFactors(num).stream().mapToInt(Integer::intValue))
              .distinct()
              .sorted()
              .toArray();

      maxScoreDiff = Integer.MIN_VALUE;
      bestK = -1;
      for (int k : primeFactors) {
        int maxSum = 0;
        int sum = 0;
        for (int num : nums) {
          sum += ((num % k == 0) ? 1 : -1) * num;
          maxSum = Math.max(maxSum, sum);
          sum = Math.max(sum, 0);
        }

        if (maxSum > maxScoreDiff) {
          maxScoreDiff = maxSum;
          bestK = k;
        }
      }
    }

    return MOD_INT.multiplyMod(maxScoreDiff, bestK);
  }

  List<Integer> buildPrimeFactors(int x) {
    List<Integer> result = new ArrayList<>();
    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        result.add(i);

        while (x % i == 0) {
          x /= i;
        }
      }
    }
    if (x != 1) {
      result.add(x);
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
