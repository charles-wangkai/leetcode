import static java.util.Map.entry;

import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  static final Map<Integer, Integer> DIGIT_TO_ROTATED =
      Map.ofEntries(entry(0, 0), entry(1, 1), entry(6, 9), entry(8, 8), entry(9, 6));
  static final int[] DIGITS =
      DIGIT_TO_ROTATED.keySet().stream().mapToInt(Integer::intValue).sorted().toArray();
  static final int DIGIT_NUM_LIMIT = 9;

  int[] indices = new int[DIGIT_NUM_LIMIT];

  public int confusingNumberII(int n) {
    int result = (n == 1_000_000_000) ? 1 : 0;
    int limit = pow(DIGITS.length, DIGIT_NUM_LIMIT) - 1;
    for (int code = 0; code <= limit; ++code) {
      decode(code);
      int value = 0;
      for (int index : indices) {
        value = value * 10 + DIGITS[index];
      }
      if (value > n) {
        break;
      }

      if (value >= 1 && isConfusing(value)) {
        ++result;
      }
    }

    return result;
  }

  boolean isConfusing(int x) {
    String s = String.valueOf(x);
    for (int i = 0, j = s.length() - 1; i <= j; ++i, --j) {
      if (DIGIT_TO_ROTATED.get(s.charAt(i) - '0') != s.charAt(j) - '0') {
        return true;
      }
    }

    return false;
  }

  int pow(int base, int exponent) {
    return IntStream.range(0, exponent).reduce(1, (x, y) -> x * base);
  }

  void decode(int code) {
    for (int i = indices.length - 1; i >= 0; --i) {
      indices[i] = code % DIGITS.length;
      code /= DIGITS.length;
    }
  }
}
