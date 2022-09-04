import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public boolean isStrictlyPalindromic(int n) {
    return IntStream.rangeClosed(2, n - 2).allMatch(i -> isPalindrome(n, i));
  }

  boolean isPalindrome(int n, int base) {
    List<Integer> digits = new ArrayList<>();
    while (n != 0) {
      digits.add(n % base);
      n /= base;
    }

    for (int i = 0, j = digits.size() - 1; i < j; ++i, --j) {
      if (!digits.get(i).equals(digits.get(j))) {
        return false;
      }
    }

    return true;
  }
}