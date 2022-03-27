import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long[] kthPalindrome(int[] queries, int intLength) {
    int limit = pow10((intLength + 1) / 2) - pow10((intLength + 1) / 2 - 1);

    return Arrays.stream(queries)
        .mapToLong(
            query -> {
              if (query > limit) {
                return -1;
              }

              int half = pow10((intLength + 1) / 2 - 1) + query - 1;

              return Long.parseLong(
                  String.format(
                      "%d%s",
                      half,
                      new StringBuilder(String.valueOf(half))
                          .reverse()
                          .toString()
                          .substring((intLength % 2 == 0) ? 0 : 1)));
            })
        .toArray();
  }

  static int pow10(int exponent) {
    return IntStream.range(0, exponent).reduce(1, (x, y) -> x * 10);
  }
}