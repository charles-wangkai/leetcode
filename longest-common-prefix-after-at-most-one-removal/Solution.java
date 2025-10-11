class Solution {
  public int longestCommonPrefix(String s, String t) {
    int result = 0;
    int sIndex = 0;
    boolean removed = false;
    int tIndex = 0;
    while (tIndex != t.length()
        && sIndex != s.length()
        && (s.charAt(sIndex) == t.charAt(tIndex) || !removed)) {
      if (s.charAt(sIndex) == t.charAt(tIndex)) {
        ++result;
        ++tIndex;
      } else {
        removed = true;
      }

      ++sIndex;
    }

    return result;
  }
}