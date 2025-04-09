class Solution {
  public int longestPalindrome(String s, String t) {
    int result = 0;
    for (int sBeginIndex = 0; sBeginIndex < s.length(); ++sBeginIndex) {
      for (int sEndIndex = sBeginIndex; sEndIndex <= s.length(); ++sEndIndex) {
        for (int tBeginIndex = 0; tBeginIndex < t.length(); ++tBeginIndex) {
          for (int tEndIndex = tBeginIndex; tEndIndex <= t.length(); ++tEndIndex) {
            if (isPalindrome(
                s.substring(sBeginIndex, sEndIndex) + t.substring(tBeginIndex, tEndIndex))) {
              result = Math.max(result, (sEndIndex - sBeginIndex) + (tEndIndex - tBeginIndex));
            }
          }
        }
      }
    }

    return result;
  }

  boolean isPalindrome(String str) {
    return new StringBuilder(str).reverse().toString().equals(str);
  }
}