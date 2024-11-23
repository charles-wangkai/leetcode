import java.util.Arrays;

class Solution {
  public char[][] rotateTheBox(char[][] box) {
    int m = box.length;
    int n = box[0].length;

    char[][] result = new char[n][m];
    for (int r = 0; r < result.length; ++r) {
      Arrays.fill(result[r], '.');
    }
    for (int r = 0; r < m; ++r) {
      int lastC = n - 1;
      for (int c = n - 1; c >= 0; --c) {
        if (box[r][c] == '#') {
          while (box[r][lastC] == '*') {
            --lastC;
          }
          result[lastC][m - 1 - r] = '#';
          --lastC;
        } else if (box[r][c] == '*') {
          result[c][m - 1 - r] = '*';
          lastC = c;
        }
      }
    }

    return result;
  }
}
