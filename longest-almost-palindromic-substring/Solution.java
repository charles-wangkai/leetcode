class Solution {
  public int almostPalindromic(String s) {
    int result = 0;
    boolean[][] exacts = new boolean[s.length()][s.length()];
    boolean[][] almosts = new boolean[s.length()][s.length()];
    for (int length = 1; length <= s.length(); ++length) {
      int beginIndex = 0;
      while (true) {
        int endIndex = beginIndex + length - 1;
        if (endIndex == s.length()) {
          break;
        }

        if (length == 1) {
          exacts[beginIndex][endIndex] = true;
          almosts[beginIndex][endIndex] = true;
        } else if (length == 2) {
          exacts[beginIndex][endIndex] = s.charAt(beginIndex) == s.charAt(endIndex);
          almosts[beginIndex][endIndex] = true;
        } else {
          exacts[beginIndex][endIndex] =
              (s.charAt(beginIndex) == s.charAt(endIndex)) && exacts[beginIndex + 1][endIndex - 1];
          almosts[beginIndex][endIndex] =
              exacts[beginIndex + 1][endIndex]
                  || exacts[beginIndex][endIndex - 1]
                  || ((s.charAt(beginIndex) == s.charAt(endIndex))
                      && almosts[beginIndex + 1][endIndex - 1]);
        }

        if (almosts[beginIndex][endIndex]) {
          result = length;
        }

        ++beginIndex;
      }
    }

    return result;
  }
}