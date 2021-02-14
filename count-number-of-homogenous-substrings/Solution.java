class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countHomogenous(String s) {
    long num = 0;
    int count = 0;
    char prev = 0;
    for (int i = 0; i <= s.length(); ++i) {
      if (i != s.length() && s.charAt(i) == prev) {
        ++count;
      } else {
        num += count * (count + 1L) / 2;

        if (i != s.length()) {
          count = 1;
          prev = s.charAt(i);
        }
      }
    }

    return (int) (num % MODULUS);
  }
}
