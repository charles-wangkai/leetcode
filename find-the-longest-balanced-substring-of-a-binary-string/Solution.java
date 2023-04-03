class Solution {
  public int findTheLongestBalancedSubstring(String s) {
    for (int length = s.length(); ; --length) {
      for (int beginIndex = 0; beginIndex + length <= s.length(); ++beginIndex) {
        if (isBalanced(s.substring(beginIndex, beginIndex + length))) {
          return length;
        }
      }
    }
  }

  boolean isBalanced(String s) {
    return s.length() % 2 == 0 && s.equals("0".repeat(s.length() / 2) + "1".repeat(s.length() / 2));
  }
}
