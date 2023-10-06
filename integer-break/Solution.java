import java.util.stream.IntStream;

class Solution {
  public int integerBreak(int n) {
    if (n == 2) {
      return 1;
    }
    if (n == 3) {
      return 2;
    }

    int num2 = 0;
    while ((n - num2 * 2) % 3 != 0) {
      ++num2;
    }

    return pow(2, num2) * pow(3, (n - num2 * 2) / 3);
  }

  int pow(int base, int exponent) {
    return IntStream.range(0, exponent).reduce(1, (acc, x) -> acc * base);
  }
}
