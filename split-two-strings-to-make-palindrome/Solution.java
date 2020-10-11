class Solution {
  public boolean checkPalindromeFormation(String a, String b) {
    int halfLength = a.length() / 2;
    String ra = new StringBuilder(a).reverse().toString();
    String rb = new StringBuilder(b).reverse().toString();

    return check(halfLength, a, b)
        || check(halfLength, b, a)
        || check(halfLength, ra, rb)
        || check(halfLength, rb, ra);
  }

  boolean check(int halfLength, String s1, String s2) {
    boolean secondMatched = true;
    for (int i = 0; i < halfLength; ++i) {
      if (secondMatched) {
        if (s1.charAt(i) != s2.charAt(s1.length() - 1 - i)) {
          secondMatched = false;
        }
      }
      if (!secondMatched && s1.charAt(i) != s1.charAt(s1.length() - 1 - i)) {
        return false;
      }
    }

    return true;
  }
}
