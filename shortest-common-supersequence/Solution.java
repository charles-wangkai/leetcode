class Solution {
  public String shortestCommonSupersequence(String str1, String str2) {
    int[][] dp = new int[str1.length() + 1][str2.length() + 1];
    for (int i = 0; i <= str1.length(); ++i) {
      for (int j = 0; j <= str2.length(); ++j) {
        if (i == 0) {
          dp[i][j] = j;
        } else if (j == 0) {
          dp[i][j] = i;
        } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
        }
      }
    }

    StringBuilder result = new StringBuilder();
    int length1 = str1.length();
    int length2 = str2.length();
    while (length1 != 0 || length2 != 0) {
      if (length1 != 0 && dp[length1][length2] == dp[length1 - 1][length2] + 1) {
        result.append(str1.charAt(length1 - 1));

        --length1;
      } else if (length2 != 0 && dp[length1][length2] == dp[length1][length2 - 1] + 1) {
        result.append(str2.charAt(length2 - 1));

        --length2;
      } else {
        result.append(str1.charAt(length1 - 1));

        --length1;
        --length2;
      }
    }

    return result.reverse().toString();
  }
}
