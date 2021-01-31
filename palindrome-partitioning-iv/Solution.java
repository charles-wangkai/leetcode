class Solution {
  public boolean checkPartitioning(String s) {
    boolean[][] palindromes = new boolean[s.length()][s.length()];
    for (int length = 1; length <= s.length(); ++length) {
      for (int beginIndex = 0; beginIndex + length <= s.length(); ++beginIndex) {
        int endIndex = beginIndex + length - 1;
        palindromes[beginIndex][endIndex] =
            s.charAt(beginIndex) == s.charAt(endIndex)
                && (length <= 2 || palindromes[beginIndex + 1][endIndex - 1]);
      }
    }

    for (int middleBeginIndex = 1; middleBeginIndex < s.length() - 1; ++middleBeginIndex) {
      for (int middleEndIndex = middleBeginIndex;
          middleEndIndex < s.length() - 1;
          ++middleEndIndex) {
        if (palindromes[0][middleBeginIndex - 1]
            && palindromes[middleBeginIndex][middleEndIndex]
            && palindromes[middleEndIndex + 1][s.length() - 1]) {
          return true;
        }
      }
    }

    return false;
  }
}
