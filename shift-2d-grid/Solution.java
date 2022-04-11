import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
  public List<List<Integer>> shiftGrid(int[][] grid, int k) {
    int m = grid.length;
    int n = grid[0].length;
    int[][] result = new int[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        int index = (r * n + c + k) % (m * n);

        result[index / n][index % n] = grid[r][c];
      }
    }

    return Arrays.stream(result)
        .map(line -> Arrays.stream(line).boxed().collect(Collectors.toList()))
        .collect(Collectors.toList());
  }
}
