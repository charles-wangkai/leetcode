import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long countDistinct(long n) {
    int[] digits = String.valueOf(n).chars().map(c -> c - '0').toArray();

    long result =
        IntStream.rangeClosed(1, digits.length - 1).mapToLong(this::pow9).sum()
            + (Arrays.stream(digits).allMatch(digit -> digit != 0) ? 1 : 0);
    for (int i = 0; i < digits.length; ++i) {
      if (digits[i] == 0) {
        break;
      }

      for (int d = 1; d < digits[i]; ++d) {
        result += pow9(digits.length - i - 1);
      }
    }

    return result;
  }

  long pow9(int exponent) {
    long result = 1;
    for (int i = 0; i < exponent; ++i) {
      result *= 9;
    }

    return result;
  }
}