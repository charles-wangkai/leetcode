class Solution {
  public int countPalindromicSubsequence(String s) {
    int result = 0;
    for (char side = 'a'; side <= 'z'; ++side) {
      for (char middle = 'a'; middle <= 'z'; ++middle) {
        if (isSubsequence(s, String.format("%c%c%c", side, middle, side))) {
          ++result;
        }
      }
    }

    return result;
  }

  boolean isSubsequence(String s, String target) {
    int index = 0;
    for (char c : s.toCharArray()) {
      if (index != target.length() && c == target.charAt(index)) {
        ++index;
      }
    }

    return index == target.length();
  }
}
