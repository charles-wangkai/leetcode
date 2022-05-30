class Solution {
  public int divide(int dividend, int divisor) {
    long a = dividend;
    long b = divisor;
    boolean positive = true;
    if (a < 0) {
      a = -a;
      positive = !positive;
    }
    if (b < 0) {
      b = -b;
      positive = !positive;
    }

    long result = 0;
    long power = b;
    while (power << 1 <= a) {
      power <<= 1;
    }

    while (power >= b) {
      if (a >= power) {
        a -= power;
        result += power / b;
      }
      power >>= 1;
    }

    if (!positive) {
      result = -result;
    }

    return (int) Math.min(Integer.MAX_VALUE, Math.max(Integer.MIN_VALUE, result));
  }
}
