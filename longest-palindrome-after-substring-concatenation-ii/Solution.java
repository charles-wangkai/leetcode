import java.util.HashSet;
import java.util.Set;

class Solution {
  static final int BASE1 = 31;
  static final int BASE2 = 37;
  static final int MODULUS = 1_000_000_007;

  public int longestPalindrome(String s, String t) {
    return Math.max(computeMaxLength(s, reverse(t)), computeMaxLength(reverse(t), s));
  }

  int computeMaxLength(String str1, String str2) {
    Set<Long> tailHashes = new HashSet<>();
    for (int beginIndex = 0; beginIndex < str2.length(); ++beginIndex) {
      int hash1 = 0;
      int hash2 = 0;
      for (int endIndex = beginIndex; endIndex <= str2.length(); ++endIndex) {
        tailHashes.add(generateKey(endIndex - beginIndex, hash1, hash2));

        if (endIndex != str2.length()) {
          hash1 = addMod(multiplyMod(hash1, BASE1), str2.charAt(endIndex) - 'a' + 1);
          hash2 = addMod(multiplyMod(hash2, BASE2), str2.charAt(endIndex) - 'a' + 1);
        }
      }
    }

    int[] palindromeMaxLengths = new int[str1.length()];
    for (int i = 0; i < str1.length(); ++i) {
      for (int leftIndex = i, rightIndex = i;
          leftIndex >= 0
              && rightIndex < str1.length()
              && str1.charAt(leftIndex) == str1.charAt(rightIndex);
          --leftIndex, ++rightIndex) {
        palindromeMaxLengths[leftIndex] =
            Math.max(palindromeMaxLengths[leftIndex], rightIndex - leftIndex + 1);
      }

      for (int leftIndex = i, rightIndex = i + 1;
          leftIndex >= 0
              && rightIndex < str1.length()
              && str1.charAt(leftIndex) == str1.charAt(rightIndex);
          --leftIndex, ++rightIndex) {
        palindromeMaxLengths[leftIndex] =
            Math.max(palindromeMaxLengths[leftIndex], rightIndex - leftIndex + 1);
      }
    }

    int result = 0;
    for (int beginIndex = 0; beginIndex < str1.length(); ++beginIndex) {
      int hash1 = 0;
      int hash2 = 0;
      for (int endIndex = beginIndex; endIndex <= str1.length(); ++endIndex) {
        if (tailHashes.contains(generateKey(endIndex - beginIndex, hash1, hash2))) {
          result =
              Math.max(
                  result,
                  2 * (endIndex - beginIndex)
                      + ((endIndex == str1.length()) ? 0 : palindromeMaxLengths[endIndex]));
        }

        if (endIndex != str1.length()) {
          hash1 = addMod(multiplyMod(hash1, BASE1), str1.charAt(endIndex) - 'a' + 1);
          hash2 = addMod(multiplyMod(hash2, BASE2), str1.charAt(endIndex) - 'a' + 1);
        }
      }
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  String reverse(String str) {
    return new StringBuilder(str).reverse().toString();
  }

  long generateKey(int length, int hash1, int hash2) {
    return (length + 1L) * hash1 * hash2;
  }
}
