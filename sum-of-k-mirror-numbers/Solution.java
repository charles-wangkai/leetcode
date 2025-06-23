import java.util.ArrayList;
import java.util.List;

class Solution {
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
    List<Integer> digits = new ArrayList<>();
    while (value != 0) {
      digits.add((int) (value % k));

      value /= k;
    }

    for (int i = 0, j = digits.size() - 1; i < j; ++i, --j) {
      if (!digits.get(i).equals(digits.get(j))) {
        return false;
      }
    }

    return true;
  }

  int pow10(int exponent) {
    int result = 1;
    for (int i = 0; i < exponent; ++i) {
      result *= 10;
    }

    return result;
  }
}
