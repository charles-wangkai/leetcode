// https://leetcode.com/problems/shortest-palindrome/solutions/60113/clean-kmp-solution-with-super-detailed-explanation/
// https://cp-algorithms.com/string/prefix-function.html

class Solution {
  public String shortestPalindrome(String s) {
    int[] pi = prefixFunction(s + "#" + new StringBuilder(s).reverse().toString());
    String center = s.substring(0, pi[pi.length - 1]);
    String tail = s.substring(pi[pi.length - 1]);

    return new StringBuilder(tail).reverse().toString() + center + tail;
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
