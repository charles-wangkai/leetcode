public class Solution {
  public String longestPalindrome(String s) {
    String result = "";
    for (int i = 0; i < s.length(); ++i) {
      String palindrome = findMaxLengthPalindrome(s, i, i);
      if (palindrome.length() > result.length()) {
        result = palindrome;
      }
    }
    for (int i = 0; i < s.length() - 1; ++i) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        String palindrome = findMaxLengthPalindrome(s, i, i + 1);
        if (palindrome.length() > result.length()) {
          result = palindrome;
        }
      }
    }

    return result;
  }

  String findMaxLengthPalindrome(String s, int leftIndex, int rightIndex) {
    while (leftIndex >= 1
        && rightIndex + 1 < s.length()
        && s.charAt(leftIndex - 1) == s.charAt(rightIndex + 1)) {
      --leftIndex;
      ++rightIndex;
    }

    return s.substring(leftIndex, rightIndex + 1);
  }
}
