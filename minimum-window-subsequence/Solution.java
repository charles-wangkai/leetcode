public class Solution {
  public String minWindow(String S, String T) {
    int[][] minLengths = new int[S.length() + 1][T.length() + 1];

    for (int i = 0; i <= S.length(); i++) {
      for (int j = 0; j <= T.length(); j++) {
        if (j == 0) {
          minLengths[i][j] = 0;
        } else {
          minLengths[i][j] = -1;

          if (i > 0) {
            if (S.charAt(i - 1) == T.charAt(j - 1)) {
              if (minLengths[i - 1][j - 1] >= 0) {
                minLengths[i][j] = minLengths[i - 1][j - 1] + 1;
              }
            } else {
              if (minLengths[i - 1][j] >= 0) {
                minLengths[i][j] = minLengths[i - 1][j] + 1;
              }
            }
          }
        }
      }
    }

    int minLength = Integer.MAX_VALUE;
    int startIndex = Integer.MAX_VALUE;
    for (int i = 0; i <= S.length(); i++) {
      if (minLengths[i][T.length()] >= 0 && minLengths[i][T.length()] < minLength) {
        minLength = minLengths[i][T.length()];
        startIndex = i - minLength;
      }
    }
    return (minLength == Integer.MAX_VALUE) ? "" : S.substring(startIndex, startIndex + minLength);
  }
}
