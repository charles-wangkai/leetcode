import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int BIT_NUM = 50;

  public int[] findProductsOfElements(long[][] queries) {
    return Arrays.stream(queries)
        .mapToInt(
            query -> {
              int modulus = (int) query[2];

              int result = 1;
              for (int b = 0; b < BIT_NUM; ++b) {
                result =
                    multiplyMod(
                        result,
                        powMod(2, computeNumBetween(b, query[0], query[1]) * b, modulus),
                        modulus);
              }

              return result;
            })
        .toArray();
  }

  long computeNumBetween(int b, long from, long to) {
    return computeNum(b, to + 1) - computeNum(b, from);
  }

  long computeNum(int b, long limit) {
    if (limit == 0) {
      return 0;
    }

    long maxValue = computeMaxValue(limit);
    int[] bits = IntStream.range(0, BIT_NUM).filter(i -> ((maxValue >> i) & 1) == 1).toArray();
    int rest = (int) (limit - computeTotalBitNum(maxValue));

    return computeBitNum(b, maxValue)
        + (IntStream.range(0, rest).anyMatch(i -> bits[i] == b) ? 1 : 0);
  }

  long computeMaxValue(long limit) {
    long result = -1;
    long lower = 0;
    long upper = limit;
    while (lower <= upper) {
      long middle = (lower + upper) / 2;
      if (computeTotalBitNum(middle) >= limit) {
        upper = middle - 1;
      } else {
        result = middle;
        lower = middle + 1;
      }
    }

    return result;
  }

  long computeTotalBitNum(long maxValue) {
    long result = 0;
    int prefixOneCount = 0;
    for (int i = BIT_NUM - 1; i >= 0; --i) {
      if (((maxValue >> i) & 1) == 1) {
        result += (1L << i) * prefixOneCount;
        if (i != 0) {
          result += (1L << (i - 1)) * i;
        }

        ++prefixOneCount;
      }
    }

    return result;
  }

  long computeBitNum(int b, long maxValue) {
    long result = 0;
    int prefixOneCount = 0;
    for (int i = BIT_NUM - 1; i >= 0; --i) {
      if (((maxValue >> i) & 1) == 1) {
        result += (1L << i) * prefixOneCount;
        if (i > b) {
          result += 1L << (i - 1);
        }

        if (i == b) {
          ++prefixOneCount;
        }
      }
    }

    return result;
  }

  int powMod(int base, long exponent, int modulus) {
    int result = 1;
    while (exponent != 0) {
      if ((exponent & 1) == 1) {
        result = multiplyMod(result, base, modulus);
      }

      base = multiplyMod(base, base, modulus);
      exponent >>= 1;
    }

    return result;
  }

  int multiplyMod(int x, int y, int modulus) {
    return Math.floorMod((long) x * y, modulus);
  }
}