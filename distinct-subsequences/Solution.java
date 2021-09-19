class Solution {
  public int numDistinct(String s, String t) {
    int sLength = s.length();
    int tLength = t.length();
    int[][] wayNums = new int[sLength + 1][tLength + 1];
    for (int i = 0; i <= sLength; ++i) {
      for (int j = 0; j <= tLength; ++j) {
        if (j == 0) {
          wayNums[i][j] = 1;
        } else if (i == 0) {
          wayNums[i][j] = 0;
        } else {
          wayNums[i][j] =
              wayNums[i - 1][j]
                  + ((s.charAt(i - 1) == t.charAt(j - 1)) ? wayNums[i - 1][j - 1] : 0);
        }
      }
    }

    return wayNums[sLength][tLength];
  }
}
