class Solution {
  public int[][] specialGrid(int n) {
    int size = 1 << n;

    int[][] grid = new int[size][size];
    fill(grid, 0, 0, size - 1, size - 1, 0, size * size - 1);

    return grid;
  }

  void fill(int[][] grid, int minR, int minC, int maxR, int maxC, int minValue, int maxValue) {
    int size = maxR - minR + 1;
    int nextSize = size / 2;

    if (size == 1) {
      grid[minR][minC] = minValue;
    } else {
      fill(
          grid,
          minR,
          minC,
          minR + nextSize - 1,
          minC + nextSize - 1,
          maxValue - nextSize * nextSize + 1,
          maxValue);
      fill(
          grid,
          minR,
          maxC - nextSize + 1,
          minR + nextSize - 1,
          maxC,
          minValue,
          minValue + nextSize * nextSize - 1);
      fill(
          grid,
          maxR - nextSize + 1,
          minC,
          maxR,
          minC + nextSize - 1,
          maxValue - nextSize * nextSize * 2 + 1,
          maxValue - nextSize * nextSize);
      fill(
          grid,
          maxR - nextSize + 1,
          maxC - nextSize + 1,
          maxR,
          maxC,
          minValue + nextSize * nextSize,
          minValue + nextSize * nextSize * 2 - 1);
    }
  }
}