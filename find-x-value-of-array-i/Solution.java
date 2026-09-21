import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public long[] resultArray(int[] nums, int k) {
    int[] remainders = Arrays.stream(nums).map(x -> x % k).toArray();

    return IntStream.range(0, k).mapToLong(x -> computeWayNum(k, remainders, x)).toArray();
  }

  long computeWayNum(int k, int[] remainders, int x) {
    if (x == 0) {
      return computeWayNumForX0(k, remainders);
    }

    if (k == 4 && x == 2) {
      return computeWayNumForX2K4(remainders);
    }

    ModInt modInt = new ModInt(k);

    Map<Integer, Integer> prefixProductToCount = new HashMap<>();
    prefixProductToCount.put(1, 1);

    long result = 0;
    int prefixProduct = 1;
    for (int remainder : remainders) {
      if (remainder == 0 || (k == 4 && remainder == 2)) {
        prefixProductToCount.clear();
        prefixProductToCount.put(1, 1);

        prefixProduct = 1;
      } else {
        prefixProduct = modInt.multiplyMod(prefixProduct, remainder);
        result += prefixProductToCount.getOrDefault(modInt.divideMod(prefixProduct, x), 0);

        prefixProductToCount.put(
            prefixProduct, prefixProductToCount.getOrDefault(prefixProduct, 0) + 1);
      }
    }

    return result;
  }

  long computeWayNumForX0(int k, int[] remainders) {
    if (k == 4) {
      return computeWayNumForX0K4(remainders);
    }

    long result = remainders.length * (remainders.length + 1L) / 2;
    int length = 0;
    for (int i = 0; i <= remainders.length; ++i) {
      if (i != remainders.length && remainders[i] != 0) {
        ++length;
      } else {
        result -= length * (length + 1L) / 2;
        length = 0;
      }
    }

    return result;
  }

  long computeWayNumForX0K4(int[] remainders) {
    int[] evenIndices =
        IntStream.range(0, remainders.length).filter(i -> remainders[i] % 2 == 0).toArray();

    long result = 0;
    for (int i = 0; i < evenIndices.length; ++i) {
      int leftOddNum = evenIndices[i] - ((i == 0) ? -1 : evenIndices[i - 1]) - 1;

      if (remainders[evenIndices[i]] == 0) {
        result +=
            (leftOddNum + 1L)
                * (((i == evenIndices.length - 1) ? remainders.length : evenIndices[i + 1])
                    - evenIndices[i]);
      }
      if (i != evenIndices.length - 1) {
        result += (leftOddNum + 1L) * (remainders.length - evenIndices[i + 1]);
      }
    }

    return result;
  }

  long computeWayNumForX2K4(int[] remainders) {
    int[] evenIndices =
        IntStream.range(0, remainders.length).filter(i -> remainders[i] % 2 == 0).toArray();

    return IntStream.range(0, evenIndices.length)
        .filter(i -> remainders[evenIndices[i]] == 2)
        .mapToLong(
            i ->
                (long) (evenIndices[i] - ((i == 0) ? -1 : evenIndices[i - 1]))
                    * (((i == evenIndices.length - 1) ? remainders.length : evenIndices[i + 1])
                        - evenIndices[i]))
        .sum();
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
