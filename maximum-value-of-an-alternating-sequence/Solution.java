class Solution {
  public long maximumValue(int n, int s, int m) {
    if (n == 1) {
      return s;
    }
    if (n % 2 == 0) {
      return s + m + (m - 1L) * ((n - 1) / 2);
    }

    return s + (m - 1L) * ((n - 1) / 2) + 1;
  }
}