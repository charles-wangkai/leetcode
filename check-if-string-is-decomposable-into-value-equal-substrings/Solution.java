class Solution {
  public boolean isDecomposable(String s) {
    boolean hasTwo = false;
    int length = 0;
    for (int i = 0; i <= s.length(); ++i) {
      if (i != 0 && i != s.length() && s.charAt(i) == s.charAt(i - 1)) {
        ++length;
      } else {
        int remainder = length % 3;
        if (remainder == 1) {
          return false;
        } else if (remainder == 2) {
          if (hasTwo) {
            return false;
          }

          hasTwo = true;
        }

        length = 1;
      }
    }

    return hasTwo;
  }
}