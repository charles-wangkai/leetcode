import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
  public String largestPalindrome(int n, int k) {
    int[] digits = new int[n];
    Arrays.fill(digits, 9);

    if (k == 2) {
      digits[0] = 8;
      digits[digits.length - 1] = 8;
    } else if (k == 4) {
      digits[0] = 8;
      digits[digits.length - 1] = 8;
      if (n != 1) {
        digits[1] = 8;
        digits[digits.length - 2] = 8;
      }
    } else if (k == 5) {
      digits[0] = 5;
      digits[digits.length - 1] = 5;
    } else if (k == 6) {
      if (n <= 2) {
        digits[0] = 6;
        digits[digits.length - 1] = 6;
      } else {
        digits[0] = 8;
        digits[digits.length - 1] = 8;

        while (Arrays.stream(digits).sum() % 3 != 0) {
          --digits[n / 2];
          if (n % 2 == 0) {
            --digits[n / 2 - 1];
          }
        }
      }
    } else if (k == 7) {
      if (n <= 2) {
        digits[0] = 7;
        digits[digits.length - 1] = 7;
      } else {
        while (!new BigInteger(toValueString(digits))
            .mod(BigInteger.valueOf(7))
            .equals(BigInteger.ZERO)) {
          --digits[n / 2];
          if (n % 2 == 0) {
            --digits[n / 2 - 1];
          }
        }
      }
    } else if (k == 8) {
      digits[0] = 8;
      digits[digits.length - 1] = 8;
      if (n != 1) {
        digits[1] = 8;
        digits[digits.length - 2] = 8;

        if (n != 2) {
          digits[2] = 8;
          digits[digits.length - 3] = 8;
        }
      }
    }

    return toValueString(digits);
  }

  String toValueString(int[] digits) {
    return Arrays.stream(digits).mapToObj(String::valueOf).collect(Collectors.joining());
  }
}