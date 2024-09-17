// https://leetcode.com/problems/minimum-number-of-valid-strings-to-form-target-ii/solutions/5788346/kmp/
// https://cp-algorithms.com/string/prefix-function.html

class Solution {
  public int minValidStrings(String[] words, String target) {
    int[] ps = new int[target.length() + 1];
    for (String word : words) {
      int[] pi = prefixFunction(word + "#" + target);
      for (int i = 1; i <= target.length(); ++i) {
        ps[i] = Math.max(ps[i], pi[word.length() + i]);
      }
    }

    int result = 0;
    int length = target.length();
    while (length != 0 && ps[length] != 0) {
      length -= ps[length];
      ++result;
    }

    return (length == 0) ? result : -1;
  }

  int[] prefixFunction(String s) {
    int[] pi = new int[s.length()];
    for (int i = 1; i < pi.length; ++i) {
      int j = pi[i - 1];
      while (j > 0 && s.charAt(i) != s.charAt(j)) {
        j = pi[j - 1];
      }
      if (s.charAt(i) == s.charAt(j)) {
        ++j;
      }
      pi[i] = j;
    }

    return pi;
  }
}
