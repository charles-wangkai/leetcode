class Solution {
  public int countKConstraintSubstrings(String s, int k) {
    int result = 0;
    for (int beginIndex = 0; beginIndex < s.length(); ++beginIndex) {
      for (int endIndex = beginIndex; endIndex < s.length(); ++endIndex) {
        if (check(k, s.substring(beginIndex, endIndex + 1))) {
          ++result;
        }
      }
    }

    return result;
  }

  static boolean check(int k, String str) {
    int count0 = (int) str.chars().filter(c -> c == '0').count();
    int count1 = str.length() - count0;

    return count0 <= k || count1 <= k;
  }
}