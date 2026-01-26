class Solution {
  static final int BIT_NUM = 50;

  public long nthSmallest(long n, int k) {
    long result = 0;
    for (int b = BIT_NUM - 1; b >= 0; --b) {
      if (b + 1 == k || C(b, k) < n) {
        n -= C(b, k);
        --k;

        result += 1L << b;
      }
    }

    return result;
  }

  long C(int x, int r) {
    long result = 1;
    for (int i = 0; i < r; ++i) {
      result = result * (x - i) / (i + 1);
    }

    return result;
  }
}