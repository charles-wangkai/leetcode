import java.util.stream.IntStream;

class Solution {
  public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
    return findMaxPrefix(limit, s, finish) - findMinPrefix(limit, s, start) + 1;
  }

  long findMaxPrefix(int limit, String s, long finish) {
    long result = -1;
    long lower = 0;
    long upper = pow(limit + 1, String.valueOf(finish).length() - s.length());
    while (lower <= upper) {
      long middle = (lower + upper) / 2;
      if (Long.parseLong(Long.toString(middle, limit + 1) + s) <= finish) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  long findMinPrefix(int limit, String s, long start) {
    long result = -1;
    long lower = 0;
    long upper = pow(limit + 1, String.valueOf(start).length() + 1 - s.length());
    while (lower <= upper) {
      long middle = (lower + upper) / 2;
      if (Long.parseLong(Long.toString(middle, limit + 1) + s) >= start) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  long pow(int base, int exponent) {
    return IntStream.range(0, exponent).asLongStream().reduce(1, (acc, x) -> acc * base);
  }
}