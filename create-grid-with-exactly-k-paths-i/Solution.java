import java.util.Arrays;

class Solution {
  public String[] createGrid(int m, int n, int k) {
    char[][] result = new char[m][n];
    for (int r = 0; r < m; ++r) {
      Arrays.fill(result[r], '#');
    }

    if (m == 2 && k <= n) {
      for (int c = 0; c < k; ++c) {
        result[0][c] = '.';
      }
      for (int c = 0; c < n; ++c) {
        result[1][c] = '.';
      }
    } else if (n == 2 && k <= m) {
      for (int r = 0; r < k; ++r) {
        result[r][0] = '.';
      }
      for (int r = 0; r < m; ++r) {
        result[r][1] = '.';
      }
    } else if (k == 1) {
      for (int c = 0; c < n; ++c) {
        result[0][c] = '.';
      }
      for (int r = 0; r < m; ++r) {
        result[r][n - 1] = '.';
      }
    } else if (k == 2) {
      if (m == 1 || n == 1) {
        return new String[0];
      }

      for (int c = 0; c < n; ++c) {
        result[0][c] = '.';
      }
      for (int r = 0; r < m; ++r) {
        result[r][n - 1] = '.';
      }

      result[1][n - 2] = '.';
    } else if (k == 3) {
      if (m <= 2 || n <= 2) {
        return new String[0];
      }

      result[0][0] = '.';
      result[0][1] = '.';
      result[1][0] = '.';
      result[1][1] = '.';
      result[2][0] = '.';
      result[2][1] = '.';
      result[2][2] = '.';

      for (int c = 3; c < n; ++c) {
        result[2][c] = '.';
      }
      for (int r = 3; r < m; ++r) {
        result[r][n - 1] = '.';
      }
    } else {
      if (m <= 2 || n <= 2) {
        return new String[0];
      }

      result[0][0] = '.';
      result[0][1] = '.';
      result[1][0] = '.';
      result[1][1] = '.';
      result[1][2] = '.';
      result[2][1] = '.';
      result[2][2] = '.';

      for (int c = 3; c < n; ++c) {
        result[2][c] = '.';
      }
      for (int r = 3; r < m; ++r) {
        result[r][n - 1] = '.';
      }
    }

    return Arrays.stream(result).map(String::valueOf).toArray(String[]::new);
  }
}