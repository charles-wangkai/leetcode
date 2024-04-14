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
    long result = 0;
    for (int mask = 1; mask < 1 << coins.length; ++mask) {
      long lcm = -1;
      for (int i = 0; i < coins.length; ++i) {
        if (((mask >> i) & 1) == 1) {
          if (lcm == -1) {
            lcm = coins[i];
          } else {
            lcm = lcm / gcd(lcm, coins[i]) * coins[i];
          }
        }
      }

      result += ((Integer.bitCount(mask) % 2 == 1) ? 1 : -1) * (n / lcm);
    }

    return result;
  }

  long gcd(long x, long y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}