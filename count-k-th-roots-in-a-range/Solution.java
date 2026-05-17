class Solution {
  public int countKthRoots(int l, int r, int k) {
    if (k == 1) {
      return r - l + 1;
    }

    int result = 0;
    int x = 0;
    while (true) {
      long power = pow(x, k);
      if (power > r) {
        break;
      }

      if (power >= l) {
        ++result;
      }

      ++x;
    }

    return result;
  }

  long pow(int base, int exponent) {
    long result = 1;
    for (int i = 0; i < exponent; ++i) {
      result *= base;
    }

    return result;
  }
}