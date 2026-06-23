import java.util.Arrays;

class Solution {
  public String[] createGrid(int m, int n) {
    char[][] result = new char[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        result[r][c] = (r == 0 || c == n - 1) ? '.' : '#';
      }
    }

    return Arrays.stream(result).map(String::valueOf).toArray(String[]::new);
  }
}