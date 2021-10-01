class Solution {
  public int longestCommonSubsequence(String text1, String text2) {
    int[][] maxLengths = new int[text1.length() + 1][text2.length() + 1];
    for (int i = 0; i <= text1.length(); ++i) {
      for (int j = 0; j <= text2.length(); ++j) {
        if (i != 0 && j != 0) {
          if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
            maxLengths[i][j] = maxLengths[i - 1][j - 1] + 1;
          } else {
            maxLengths[i][j] = Math.max(maxLengths[i - 1][j], maxLengths[i][j - 1]);
          }
        }
      }
    }

    return maxLengths[text1.length()][text2.length()];
  }
}
