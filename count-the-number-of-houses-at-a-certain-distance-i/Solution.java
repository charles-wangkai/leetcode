class Solution {
  public int[] countOfPairs(int n, int x, int y) {
    int[][] distances = new int[n][n];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        distances[i][j] = (j == i) ? 0 : Integer.MAX_VALUE;
      }
    }
    for (int i = 0; i < n - 1; ++i) {
      distances[i][i + 1] = 1;
      distances[i + 1][i] = 1;
    }
    if (x != y) {
      distances[x - 1][y - 1] = 1;
      distances[y - 1][x - 1] = 1;
    }

    for (int k = 0; k < n; ++k) {
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
          if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE) {
            distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
          }
        }
      }
    }

    int[] result = new int[n];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        if (distances[i][j] != 0) {
          ++result[distances[i][j] - 1];
        }
      }
    }

    return result;
  }
}