class Solution {
  public long lastInteger(long n) {
    return search(n, true);
  }

  long search(long n, boolean fromLeftOrRight) {
    if (n == 1) {
      return 1;
    }

    if (n % 2 == 1) {
      return search((n + 1) / 2, !fromLeftOrRight) * 2 - 1;
    }

    if (fromLeftOrRight) {
      return search(n / 2, !fromLeftOrRight) * 2 - 1;
    }

    return search(n / 2, !fromLeftOrRight) * 2;
  }
}