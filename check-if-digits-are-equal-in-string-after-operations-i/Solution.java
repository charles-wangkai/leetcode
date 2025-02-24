import java.util.stream.IntStream;

class Solution {
  public boolean hasSameDigits(String s) {
    int[] digits = s.chars().map(c -> c - '0').toArray();
    while (digits.length != 2) {
      int[] digits_ = digits;
      digits =
          IntStream.range(0, digits.length - 1)
              .map(i -> (digits_[i] + digits_[i + 1]) % 10)
              .toArray();
    }

    return digits[0] == digits[1];
  }
}