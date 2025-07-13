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

    return search(s, s.length(), length, k);
  }

  char search(String s, int processed, long length, long k) {
    if (k >= length) {
      return '.';
    }

    char c = s.charAt(processed - 1);
    if (c == '*') {
      return search(s, processed - 1, length + 1, k);
    }
    if (c == '#') {
      return search(s, processed - 1, length / 2, k % (length / 2));
    }
    if (c == '%') {
      return search(s, processed - 1, length, length - 1 - k);
    }
    if (k == length - 1) {
      return c;
    }

    return search(s, processed - 1, length - 1, k);
  }
}