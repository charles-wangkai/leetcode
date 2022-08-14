import java.util.stream.IntStream;

class Solution {
  public int countSpecialNumbers(int n) {
    int[] targetDigits = String.valueOf(n).chars().map(c -> c - '0').toArray();

    int result = 0;
    for (int mask = 1; mask < 1 << 10; ++mask) {
      int mask_ = mask;
      int[] digits = IntStream.range(0, 10).filter(i -> (mask_ & (1 << i)) != 0).toArray();
      if (digits.length < targetDigits.length) {
        if (digits[0] == 0) {
          if (digits.length != 1) {
            result += P(digits.length - 1) * (digits.length - 1);
          }
        } else {
          result += P(digits.length);
        }
      } else if (digits.length == targetDigits.length) {
        result += search(targetDigits, digits, 0);
      }
    }

    return result;
  }

  int P(int a) {
    return IntStream.rangeClosed(1, a).reduce(1, (x, y) -> x * y);
  }

  int search(int[] targetDigits, int[] digits, int index) {
    if (index == digits.length) {
      return 1;
    }

    int result = 0;
    for (int i = index; i < digits.length; ++i) {
      swap(digits, i, index);
      if ((index != 0 || digits[index] != 0) && digits[index] <= targetDigits[index]) {
        if (digits[index] == targetDigits[index]) {
          result += search(targetDigits, digits, index + 1);
        } else {
          result += P(digits.length - index - 1);
        }
      }
      swap(digits, i, index);
    }

    return result;
  }

  void swap(int[] a, int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }
}