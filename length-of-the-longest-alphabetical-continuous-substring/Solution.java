class Solution {
  public int longestContinuousSubstring(String s) {
    int result = 0;
    int length = 0;
    for (int i = 0; i < s.length(); ++i) {
      if (i != 0 && s.charAt(i) == s.charAt(i - 1) + 1) {
        ++length;
      } else {
        length = 1;
      }

      result = Math.max(result, length);
    }

    return result;
  }
}