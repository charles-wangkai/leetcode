class Solution {
  public boolean isInterleave(String s1, String s2, String s3) {
    int s1Length = s1.length();
    int s2Length = s2.length();
    int s3Length = s3.length();
    if (s1Length + s2Length != s3Length) {
      return false;
    }

    boolean[][] interleaves = new boolean[s1Length + 1][s2Length + 1];
    for (int i = 0; i <= s1Length; ++i) {
      for (int j = 0; j <= s2Length; ++j) {
        int totalLength = i + j;
        if (totalLength == 0) {
          interleaves[i][j] = true;
        } else {
          char ch = s3.charAt(totalLength - 1);
          interleaves[i][j] =
              (i != 0 && ch == s1.charAt(i - 1) && interleaves[i - 1][j])
                  || (j != 0 && ch == s2.charAt(j - 1) && interleaves[i][j - 1]);
        }
      }
    }

    return interleaves[s1Length][s2Length];
  }
}
