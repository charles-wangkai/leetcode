class Solution {
  public int countValidPrefixes(String s) {
    int result = 0;
    int diff = 0;
    for (char c : s.toCharArray()) {
      diff += (c == '1') ? 1 : -1;

      if (Math.abs(diff) <= 1) {
        ++result;
      }
    }

    return result;
  }
}