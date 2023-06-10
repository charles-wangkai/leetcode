class Solution {
  public int longestSemiRepetitiveSubstring(String s) {
    int result = 0;
    boolean hasSame = false;
    int endIndex = 0;
    for (int beginIndex = 0; beginIndex < s.length(); ++beginIndex) {
      while (endIndex != s.length() - 1
          && (!hasSame || s.charAt(endIndex + 1) != s.charAt(endIndex))) {
        if (s.charAt(endIndex + 1) == s.charAt(endIndex)) {
          hasSame = true;
        }

        ++endIndex;
      }

      result = Math.max(result, endIndex - beginIndex + 1);

      if (beginIndex != s.length() - 1 && s.charAt(beginIndex) == s.charAt(beginIndex + 1)) {
        hasSame = false;
      }
    }

    return result;
  }
}
