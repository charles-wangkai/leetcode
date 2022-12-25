class Solution {
  public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
    int result = -1;
    int lower = 1;
    int upper = Integer.MAX_VALUE;
    while (lower <= upper) {
      int middle = lower + (upper - lower) / 2;
      if (check(divisor1, divisor2, uniqueCnt1, uniqueCnt2, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2, int maxValue) {
    int g = gcd(divisor1, divisor2);

    return maxValue
            - maxValue / divisor1
            - maxValue / divisor2
            + maxValue / ((long) divisor1 * divisor2 / g)
        >= Math.max(0, uniqueCnt1 - (maxValue / divisor2 - maxValue / divisor2 / (divisor1 / g)))
            + Math.max(
                0, uniqueCnt2 - (maxValue / divisor1 - maxValue / divisor1 / (divisor2 / g)));
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
