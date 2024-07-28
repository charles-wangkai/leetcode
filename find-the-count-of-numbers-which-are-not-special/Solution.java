class Solution {
  public int nonSpecialCount(int l, int r) {
    int result = r - l + 1;
    for (int i = 2; i * i <= r; ++i) {
      if (i * i >= l && isPrime(i)) {
        --result;
      }
    }

    return result;
  }

  boolean isPrime(int x) {
    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}