class Solution {
  public int[][] rotateGrid(int[][] grid, int k) {
    int m = grid.length;
    int n = grid[0].length;

    for (int minR = 0, maxR = m - 1, minC = 0, maxC = n - 1;
        minR < maxR && minC < maxC;
        ++minR, --maxR, ++minC, --maxC) {
      int rotationNum = k % ((maxR - minR) * 2 + (maxC - minC) * 2);
      for (int i = 0; i < rotationNum; ++i) {
        int temp = grid[minR][minC];
        for (int c = minC; c < maxC; ++c) {
          grid[minR][c] = grid[minR][c + 1];
        }
        for (int r = minR; r < maxR; ++r) {
          grid[r][maxC] = grid[r + 1][maxC];
        }
        for (int c = maxC; c > minC; --c) {
          grid[maxR][c] = grid[maxR][c - 1];
        }
        for (int r = maxR; r > minR + 1; --r) {
          grid[r][minC] = grid[r - 1][minC];
        }
        grid[minR + 1][minC] = temp;
      }
    }

    return grid;
  }
}
