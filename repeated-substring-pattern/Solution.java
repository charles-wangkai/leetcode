class Solution {
  public boolean repeatedSubstringPattern(String s) {
    for (int i = 1; i * i <= s.length(); ++i) {
      if (s.length() % i == 0
          && (s.length() != 1 && s.equals(s.substring(0, i).repeat(s.length() / i))
              || (i != 1 && s.equals(s.substring(0, s.length() / i).repeat(i))))) {
        return true;
      }
    }

    return false;
  }
}
