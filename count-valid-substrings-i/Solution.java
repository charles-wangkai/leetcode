class Solution {
  public long countValidSubstrings(String s) {
    long result = 0;
    int endIndex = -1;
    for (int beginIndex = 0; beginIndex < s.length(); ++beginIndex) {
      while (endIndex != s.length() - 1
          && (endIndex <= beginIndex
              || !(s.charAt(endIndex - 1) == 'a'
                  && s.charAt(endIndex) == 'b'
                  && s.charAt(endIndex + 1) == 'c'))) {
        ++endIndex;
      }

      result += endIndex - beginIndex + 1;
    }

    return result;
  }
}