class Solution {
  public int numSub(String s) {
    long result = 0;
    int count = 0;
    for (int i = 0; i <= s.length(); ++i) {
      if (i != s.length() && s.charAt(i) == '1') {
        ++count;
      } else {
        result += count * (count + 1L) / 2;

        count = 0;
      }
    }

    return (int) (result % 1_000_000_007);
  }
}