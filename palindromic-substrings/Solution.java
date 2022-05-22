class Solution {
  public int countSubstrings(String s) {
    int result = 0;
    for (int i = 0; i < s.length(); ++i) {
      for (int offset = 0; offset <= 1; ++offset) {
        for (int j = i, k = i + offset;
            j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k);
            --j, ++k) {
          ++result;
        }
      }
    }

    return result;
  }
}
