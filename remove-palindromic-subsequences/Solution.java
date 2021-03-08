class Solution {
  public int removePalindromeSub(String s) {
    if (s.isEmpty()) {
      return 0;
    }
    if (s.equals(new StringBuilder(s).reverse().toString())) {
      return 1;
    }

    return 2;
  }
}
