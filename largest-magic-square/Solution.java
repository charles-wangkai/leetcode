import java.util.stream.IntStream;

class Solution {
  public int largestMagicSquare(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] rowPrefixSums = new int[m][n];
    int[][] colPrefixSums = new int[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        rowPrefixSums[r][c] = ((c == 0) ? 0 : rowPrefixSums[r][c - 1]) + grid[r][c];
        colPrefixSums[r][c] = ((r == 0) ? 0 : colPrefixSums[r - 1][c]) + grid[r][c];
      }
    }

    for (int size = Math.min(m, n); ; --size) {
      for (int minR = 0; minR + size <= m; ++minR) {
        for (int minC = 0; minC + size <= n; ++minC) {
          if (check(grid, rowPrefixSums, colPrefixSums, minR, minC, size)) {
            return size;
          }
        }
      }
    }
  }

  boolean check(
      int[][] grid, int[][] rowPrefixSums, int[][] colPrefixSums, int minR, int minC, int size) {
    int diagonalSum1 = IntStream.range(0, size).map(i -> grid[minR + i][minC + i]).sum();
    int diagonalSum2 = IntStream.range(0, size).map(i -> grid[minR + i][minC + size - 1 - i]).sum();

    return diagonalSum2 == diagonalSum1
        && IntStream.range(minR, minR + size)
            .allMatch(
                r ->
                    computePrefixSum(rowPrefixSums, r, minC - 1, r, minC + size - 1)
                        == diagonalSum1)
        && IntStream.range(minC, minC + size)
            .allMatch(
                c ->
                    computePrefixSum(colPrefixSums, minR - 1, c, minR + size - 1, c)
                        == diagonalSum1);
  }

  int computePrefixSum(int[][] prefixSums, int beginR, int beginC, int endR, int endC) {
    return prefixSums[endR][endC]
        - ((beginR == -1 || beginC == -1) ? 0 : prefixSums[beginR][beginC]);
  }
}
