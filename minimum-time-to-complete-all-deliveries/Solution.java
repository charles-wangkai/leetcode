class Solution {
  public long minimumTime(int[] d, int[] r) {
    long result = -1;
    long lower = 1;
    long upper = 2L * d[0] + 2 * d[1];
    while (lower <= upper) {
      long middle = (lower + upper) / 2;
      if (check(d, r, middle)) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  boolean check(int[] d, int[] r, long totalTime) {
    long delivery1 = totalTime - totalTime / r[0];
    long delivery2 = totalTime - totalTime / r[1];

    long both = totalTime - (totalTime / r[0] + totalTime / r[1] - totalTime / lcm(r[0], r[1]));
    long only1 = delivery1 - both;
    long only2 = delivery2 - both;

    return Math.max(0, d[0] - only1) + Math.max(0, d[1] - only2) <= both;
  }

  long lcm(long x, long y) {
    return x / gcd(x, y) * y;
  }

  long gcd(long x, long y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}