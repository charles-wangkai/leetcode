import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
  public String findLexSmallestString(String s, int a, int b) {
    int[] result = s.chars().map(ch -> ch - '0').toArray();

    int[] digits = Arrays.copyOf(result, result.length);
    for (int i = 0; i < s.length(); ++i) {
      if (b % 2 == 0) {
        for (int j = 0; j < 10; ++j) {
          add(digits, a, 1);

          if (isLess(digits, result)) {
            result = Arrays.copyOf(digits, digits.length);
          }
        }
      } else {
        for (int j = 0; j < 10; ++j) {
          add(digits, a, 0);
          for (int k = 0; k < 10; ++k) {
            add(digits, a, 1);

            if (isLess(digits, result)) {
              result = Arrays.copyOf(digits, digits.length);
            }
          }
        }
      }

      rotate(digits, b);
    }

    return Arrays.stream(result).mapToObj(x -> String.valueOf(x)).collect(Collectors.joining());
  }

  void add(int[] digits, int a, int beginIndex) {
    for (int i = beginIndex; i < digits.length; i += 2) {
      digits[i] = (digits[i] + a) % 10;
    }
  }

  void rotate(int[] digits, int b) {
    int[] origin = Arrays.copyOf(digits, digits.length);
    for (int i = 0; i < origin.length; ++i) {
      digits[(i + b) % origin.length] = origin[i];
    }
  }

  boolean isLess(int[] digits1, int[] digits2) {
    int index = 0;
    while (index != digits1.length && digits1[index] == digits2[index]) {
      ++index;
    }

    return index != digits1.length && digits1[index] < digits2[index];
  }
}
