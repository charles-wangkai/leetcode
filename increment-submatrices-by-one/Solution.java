class Solution {
  public int[][] rangeAddQueries(int n, int[][] queries) {
    int[][] result = new int[n][n];
    for (int r = 0; r < n; ++r) {
      int[] deltas = new int[n];
      for (int[] query : queries) {
        if (r >= query[0] && r <= query[2]) {
          ++deltas[query[1]];
          if (query[3] != n - 1) {
            --deltas[query[3] + 1];
          }
        }
      }

      int value = 0;
      for (int c = 0; c < n; ++c) {
        value += deltas[c];
        result[r][c] = value;
      }
    }

    return result;
  }
}
