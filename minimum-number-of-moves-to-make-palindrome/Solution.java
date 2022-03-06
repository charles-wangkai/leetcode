// https://leetcode.com/problems/minimum-number-of-moves-to-make-palindrome/discuss/1822174/C%2B%2BPython-Short-Greedy-Solution

class Solution {
  public int minMovesToMakePalindrome(String s) {
    int result = 0;
    while (!s.isEmpty()) {
      int index = s.indexOf(s.charAt(s.length() - 1));
      if (index == s.length() - 1) {
        result += s.length() / 2;
        s = s.substring(0, s.length() - 1);
      } else {
        result += index;
        s = String.format("%s%s", s.substring(0, index), s.substring(index + 1, s.length() - 1));
      }
    }

    return result;
  }
}