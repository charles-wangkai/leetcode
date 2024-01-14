class Solution {
  static final long LIMIT = 10_000_000_000_000_000L;

  public long findMaximumNumber(long k, int x) {
    long result = -1;
    long lower = 1;
    long upper = LIMIT;
    while (lower <= upper) {
      long middle = (lower + upper) / 2;
      if (computePriceSum(x, middle + 1) <= k) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  long computePriceSum(int x, long maxValue) {
    String s = Long.toBinaryString(maxValue);

    long result = 0;
    int leftPrice = 0;
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == '1') {
        int rightNum = s.length() - i - 1;
        result += leftPrice * (1L << rightNum) + (1L << rightNum) / 2 * (rightNum / x);

        if ((s.length() - i) % x == 0) {
          ++leftPrice;
        }
      }
    }

    return result;
  }
}