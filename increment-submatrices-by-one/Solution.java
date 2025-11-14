class Solution {
  public int[][] rangeAddQueries(int n, int[][] queries) {
    int[][] result = new int[n][n];
    for (int r = 0; r < n; ++r) {
      int[] deltas = new int[n];
      for (int[] query : queries) {
        int minR = query[0];
        int minC = query[1];
        int maxR = query[2];
        int maxC = query[3];

        if (r >= minR && r <= maxR) {
          ++deltas[minC];
          if (maxC != n - 1) {
            --deltas[maxC + 1];
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
