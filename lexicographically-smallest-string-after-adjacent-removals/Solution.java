// https://leetcode.com/problems/lexicographically-smallest-string-after-adjacent-removals/solutions/6778382/bruteforce-to-optimal-dp-c/

class Solution {
  public String lexicographicallySmallestString(String s) {
    String[][] dp = new String[s.length() + 1][s.length() + 1];
    for (int i = 0; i < dp.length; ++i) {
      dp[i][i] = "";
    }

    for (int length = 1; length <= s.length(); ++length) {
      for (int i = 0; i + length <= s.length(); ++i) {
        int j = i + length;

        dp[i][j] = s.charAt(i) + dp[i + 1][j];

        for (int k = i + 1; k < j; ++k) {
          if (isConsecutive(s.charAt(i), s.charAt(k))
              && dp[i + 1][k].isEmpty()
              && dp[k + 1][j].compareTo(dp[i][j]) < 0) {
            dp[i][j] = dp[k + 1][j];
          }
        }
      }
    }

    return dp[0][s.length()];
  }

  boolean isConsecutive(char letter1, char letter2) {
    return Math.abs(letter1 - letter2) == 1 || Math.abs(letter1 - letter2) == 25;
  }
}
