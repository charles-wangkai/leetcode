import java.util.stream.IntStream;

class Solution {
  public int atMostNGivenDigitSet(String[] digits, int n) {
    String strN = String.valueOf(n);

    int result = IntStream.range(1, strN.length()).map(i -> pow(digits.length, i)).sum();

    boolean digitInSet = true;
    for (int i = 0; i < strN.length() && digitInSet; ++i) {
      char digit = strN.charAt(i);

      digitInSet = false;
      for (String d : digits) {
        char buildingDigit = d.charAt(0);

        if (buildingDigit < digit) {
          result += pow(digits.length, strN.length() - i - 1);
        } else if (buildingDigit == digit) {
          digitInSet = true;
        }
      }
    }
    if (digitInSet) {
      ++result;
    }

    return result;
  }

  static int pow(int base, int exponent) {
    return IntStream.range(0, exponent).map(i -> base).reduce(1, (x, y) -> x * y);
  }
}
