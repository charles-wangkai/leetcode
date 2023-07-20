class Solution {
  static final int DIGITS_MAX_SIZE = 100;

  int[] digits = new int[DIGITS_MAX_SIZE];

  public long kMirror(int k, int n) {
    long result = 0;
    for (int length = 1; ; ++length) {
      int limit = pow10((length + 1) / 2);
      for (int half = pow10((length - 1) / 2); half < limit; ++half) {
        String s;
        if (length % 2 == 0) {
          s = half + new StringBuilder(String.valueOf(half)).reverse().toString();
        } else {
          s = half + new StringBuilder(String.valueOf(half)).reverse().substring(1);
        }

        long value = Long.parseLong(s);
        if (isPalindromeInBaseK(value, k)) {
          result += value;
          --n;
          if (n == 0) {
            return result;
          }
        }
      }
    }
  }

  boolean isPalindromeInBaseK(long value, int k) {
    int size = 0;
    while (value != 0) {
      digits[size] = (int) (value % k);
      ++size;
      value /= k;
    }

    for (int i = 0, j = size - 1; i < j; ++i, --j) {
      if (digits[i] != digits[j]) {
        return false;
      }
    }

    return true;
  }

  int pow10(int x) {
    int result = 1;
    for (int i = 0; i < x; ++i) {
      result *= 10;
    }

    return result;
  }
}
