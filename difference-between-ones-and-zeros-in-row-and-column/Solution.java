import java.util.stream.IntStream;

class Solution {
  public int[][] onesMinusZeros(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] rowCounts = new int[2][m];
    int[][] colCounts = new int[2][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        ++rowCounts[grid[r][c]][r];
        ++colCounts[grid[r][c]][c];
      }
    }

    return IntStream.range(0, m)
        .mapToObj(
            r ->
                IntStream.range(0, n)
                    .map(c -> rowCounts[1][r] + colCounts[1][c] - rowCounts[0][r] - colCounts[0][c])
                    .toArray())
        .toArray(int[][]::new);
  }
}
