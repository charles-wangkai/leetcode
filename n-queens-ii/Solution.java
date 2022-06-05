class Solution {
  public int totalNQueens(int n) {
    return search(new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1], 0);
  }

  int search(boolean[] columns, boolean[] diffs, boolean[] sums, int r) {
    int n = columns.length;

    if (r == n) {
      return 1;
    }

    int result = 0;
    for (int c = 0; c < n; ++c) {
      if (!columns[c] && !diffs[r - c + (n - 1)] && !sums[r + c]) {
        columns[c] = true;
        diffs[r - c + (n - 1)] = true;
        sums[r + c] = true;

        result += search(columns, diffs, sums, r + 1);

        sums[r + c] = false;
        diffs[r - c + (n - 1)] = false;
        columns[c] = false;
      }
    }

    return result;
  }
}
