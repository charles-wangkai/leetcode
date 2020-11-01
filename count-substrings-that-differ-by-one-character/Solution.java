class Solution {
  public int countSubstrings(String s, String t) {
    int result = 0;
    for (int i = 0; i < s.length(); ++i) {
      for (int j = i; j < s.length(); ++j) {
        String sub1 = s.substring(i, j + 1);

        for (int p = 0; p + sub1.length() <= t.length(); ++p) {
          String sub2 = t.substring(p, p + sub1.length());

          if (isOneDiff(sub1, sub2)) {
            ++result;
          }
        }
      }
    }

    return result;
  }

  boolean isOneDiff(String sub1, String sub2) {
    int diffCount = 0;
    for (int i = 0; i < sub1.length(); ++i) {
      if (sub1.charAt(i) != sub2.charAt(i)) {
        ++diffCount;
      }
    }

    return diffCount == 1;
  }
}
