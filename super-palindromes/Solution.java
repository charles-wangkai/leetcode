import java.util.ArrayList;
import java.util.List;

class Solution {
  static List<Long> superPalindromes = buildSuperPalindromes();

  public int superpalindromesInRange(String left, String right) {
    return (int)
        superPalindromes.stream()
            .filter(
                superPalindrome ->
                    superPalindrome >= Long.parseLong(left)
                        && superPalindrome <= Long.parseLong(right))
            .count();
  }

  static List<Long> buildSuperPalindromes() {
    List<Long> superPalindromes = new ArrayList<>();
    for (int half = 1; half < 100000; ++half) {
      checkAndAdd(superPalindromes, buildPalindromeWithoutLast(half));

      if (half < 10000) {
        checkAndAdd(superPalindromes, buildPalindromeWithLast(half));
      }
    }
    return superPalindromes;
  }

  static int buildPalindromeWithoutLast(int half) {
    return Integer.parseInt(
        String.valueOf(half) + new StringBuilder(String.valueOf(half)).reverse().substring(1));
  }

  static int buildPalindromeWithLast(int half) {
    return Integer.parseInt(
        String.valueOf(half) + new StringBuilder(String.valueOf(half)).reverse().toString());
  }

  static void checkAndAdd(List<Long> superPalindromes, int root) {
    long square = (long) root * root;
    if (isPalindrome(square)) {
      superPalindromes.add(square);
    }
  }

  static boolean isPalindrome(long n) {
    return n == Long.parseLong(new StringBuilder(String.valueOf(n)).reverse().toString());
  }
}
