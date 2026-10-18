class Solution {
  public char kthCharacter(String s, long k) {
    int repeat = 0;
    long beginIndex = -1;
    long endIndex = -1;
    for (int i = 0; ; ++i) {
      char c = s.charAt(i);
      if (c == ' ') {
        repeat = 0;
      } else {
        ++repeat;
      }

      beginIndex = endIndex + 1;
      endIndex = beginIndex + Math.max(1, repeat) - 1;

      if (endIndex >= k) {
        return c;
      }
    }
  }
}