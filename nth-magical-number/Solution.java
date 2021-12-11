class Solution {
  static final int MODULUS = 1_000_000_007;

  public int nthMagicalNumber(int n, int a, int b) {
    long result = -1;
    long lower = 2;
    long upper = (long) n * Math.min(a, b);
    while (lower <= upper) {
      long middle = (lower + upper) / 2;

      if (computeLENum(a, b, middle) >= n) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return (int) (result % MODULUS);
  }

  long computeLENum(int a, int b, long x) {
    return x / a + x / b - x / lcm(a, b);
  }

  int lcm(int x, int y) {
    return x / gcd(x, y) * y;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
