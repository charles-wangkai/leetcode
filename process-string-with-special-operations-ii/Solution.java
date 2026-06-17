class Solution {
  public char processStr(String s, long k) {
    long length = 0;
    for (char c : s.toCharArray()) {
      if (c == '*') {
        length = Math.max(0, length - 1);
      } else if (c == '#') {
        length *= 2;
      } else if (c != '%') {
        ++length;
      }
    }
    if (k >= length) {
      return '.';
    }

    for (int i = s.length() - 1; ; --i) {
      char c = s.charAt(i);
      if (c == '*') {
        ++length;
      } else if (c == '#') {
        length /= 2;
        k %= length;
      } else if (c == '%') {
        k = length - 1 - k;
      } else if (k == length - 1) {
        return c;
      } else {
        --length;
      }
    }
  }
}