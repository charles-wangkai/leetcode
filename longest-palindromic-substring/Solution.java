class Solution {
  public String longestPalindrome(String s) {
    String result = "";
    for (int i = 0; i < s.length(); ++i) {
      for (int offset = 0; offset <= ((i == s.length() - 1) ? 0 : 1); ++offset) {
        String palindrome = findMaxLengthPalindrome(s, i, i + offset);
        if (palindrome.length() > result.length()) {
          result = palindrome;
        }
      }
    }

    return result;
  }

  String findMaxLengthPalindrome(String s, int leftIndex, int rightIndex) {
    if (s.charAt(leftIndex) != s.charAt(rightIndex)) {
      return "";
    }

    while (leftIndex != 0
        && rightIndex != s.length() - 1
        && s.charAt(leftIndex - 1) == s.charAt(rightIndex + 1)) {
      --leftIndex;
      ++rightIndex;
    }

    return s.substring(leftIndex, rightIndex + 1);
  }
}
