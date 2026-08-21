import java.util.stream.IntStream;

class Solution {
  static final long LIMIT = 50_000_000_000L;

  public long findKthSmallest(int[] coins, int k) {
    long result = -1;
    long lower = 1;
    long upper = LIMIT;
    while (lower <= upper) {
      long middle = (lower + upper) / 2;
      if (computeLessEqualNum(coins, middle) >= k) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  long computeLessEqualNum(int[] coins, long n) {
    return IntStream.range(1, 1 << coins.length)
        .mapToLong(
            mask ->
                ((Integer.bitCount(mask) % 2 == 1) ? 1 : -1)
                    * (n
                        / IntStream.range(0, coins.length)
                            .filter(i -> ((mask >> i) & 1) == 1)
                            .map(i -> coins[i])
                            .asLongStream()
                            .reduce(this::lcm)
                            .getAsLong()))
        .sum();
  }

  long lcm(long x, long y) {
    return x / gcd(x, y) * y;
  }

  long gcd(long x, long y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}