class Solution {
  public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
    for (int beginR = x, endR = x + k - 1; beginR < endR; ++beginR, --endR) {
      for (int c = y; c < y + k; ++c) {
        swap(grid, beginR, c, endR, c);
      }
    }

    return grid;
  }

  void swap(int[][] a, int r1, int c1, int r2, int c2) {
    int temp = a[r1][c1];
    a[r1][c1] = a[r2][c2];
    a[r2][c2] = temp;
  }
}