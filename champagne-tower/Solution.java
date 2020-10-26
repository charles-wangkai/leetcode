class Solution {
  public double champagneTower(int poured, int query_row, int query_glass) {
    double[] row = {poured};
    while (row.length < query_row + 1) {
      double[] nextRow = new double[row.length + 1];

      for (int i = 0; i < row.length; ++i) {
        nextRow[i] += Math.max(0, row[i] - 1) / 2;
        nextRow[i + 1] += Math.max(0, row[i] - 1) / 2;
      }

      row = nextRow;
    }

    return Math.min(1, row[query_glass]);
  }
}
