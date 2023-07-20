class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numDecodings(String s) {
    int prev = -1;
    int curr = 1;
    for (int i = 0; i < s.length(); ++i) {
      int next = multiplyMod(curr, countWayNum(s.substring(i, i + 1)));
      if (i >= 1) {
        next = addMod(next, multiplyMod(prev, countWayNum(s.substring(i - 1, i + 1))));
      }

      prev = curr;
      curr = next;
    }

    return curr;
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }

  int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }

  int countWayNum(String part) {
    if (part.length() == 1) {
      char ch = part.charAt(0);

      if (ch == '*') {
        return 9;
      } else if (ch == '0') {
        return 0;
      } else {
        return 1;
      }
    }

    char ch1 = part.charAt(0);
    char ch2 = part.charAt(1);

    if (ch1 == '*') {
      if (ch2 == '*') {
        return 15;
      } else if (ch2 <= '6') {
        return 2;
      } else {
        return 1;
      }
    } else if (ch1 == '0') {
      return 0;
    } else if (ch1 == '1') {
      if (ch2 == '*') {
        return 9;
      } else {
        return 1;
      }
    } else if (ch1 == '2') {
      if (ch2 == '*') {
        return 6;
      } else if (ch2 <= '6') {
        return 1;
      } else {
        return 0;
      }
    } else {
      return 0;
    }
  }
}
